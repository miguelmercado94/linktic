package com.linktic.test.inventario_service.application.usecase;

import com.linktic.test.inventario_service.application.client.ProductoClient;
import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import com.linktic.test.inventario_service.domain.service.IHistorialCompraDomainService;
import com.linktic.test.inventario_service.domain.service.IInventarioDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventarioUseCase {

    private final IInventarioDomainService inventarioService;
    private final IHistorialCompraDomainService historialService;
    private final ProductoClient productoClient;

    public Mono<Integer> consultarCantidadDisponible(UUID productoId) {
        return productoClient.existeProducto(productoId)
                .flatMap(existe -> {
                    if (!existe) return Mono.error(new RuntimeException("Producto no existe"));

                    return inventarioService.buscarPorProductoId(productoId)
                            .map(InventarioAggregate::getCantidad)
                            .switchIfEmpty(Mono.error(new RuntimeException("Inventario no encontrado")));
                });
    }

    public Mono<InventarioAggregate> actualizarCantidad(UUID inventarioId, int delta) {
        return delta >= 0
                ? inventarioService.aumentarCantidad(inventarioId, delta)
                : inventarioService.disminuirCantidad(inventarioId, Math.abs(delta));
    }

    public Mono<CompraResult> realizarCompra(UUID productoId, int cantidadSolicitada, String observaciones) {
        return productoClient.existeProducto(productoId)
                .flatMap(existe -> {
                    if (!existe) return Mono.error(new RuntimeException("Producto no existe"));

                    return inventarioService.buscarPorProductoId(productoId)
                            .switchIfEmpty(Mono.error(new RuntimeException("Inventario no encontrado")))
                            .flatMap(inventario -> {
                                if (inventario.getCantidad() < cantidadSolicitada) {
                                    return Mono.error(new RuntimeException("Inventario insuficiente"));
                                }

                                InventarioAggregate actualizado = inventario.descontar(cantidadSolicitada);

                                return inventarioService.disminuirCantidad(actualizado.getId(), cantidadSolicitada)
                                        .then(historialService.registrarCompra(actualizado.getId(), cantidadSolicitada, observaciones))
                                        .map(historial -> new CompraResult(
                                                actualizado.getId(),
                                                cantidadSolicitada,
                                                actualizado.getCantidad(),
                                                historial.getFecha(),
                                                observaciones
                                        ));
                            });
                });
    }

    public Mono<HistorialCompra> registrarManual(UUID inventarioId, int cantidad, String observaciones) {
        return historialService.registrarCompra(inventarioId, cantidad, observaciones);
    }

    public Flux<HistorialCompra> obtenerHistorial(UUID inventarioId) {
        return historialService.findByInventarioId(inventarioId);
    }

    public Mono<Void> emitirEventoCambio(UUID inventarioId) {
        return inventarioService.findById(inventarioId)
                .doOnNext(inv -> System.out.println("Evento: inventario cambiado -> " + inv.getId()))
                .then();
    }

    // DTO para compra
    public record CompraResult(
            UUID inventarioId,
            int cantidadComprada,
            int cantidadRestante,
            LocalDateTime fecha,
            String observaciones
    ) {}
}
