package com.linktic.test.inventario_service.interfaces.rest;

import com.linktic.test.inventario_service.application.dto.request.CompraRequest;
import com.linktic.test.inventario_service.application.dto.request.StockRequest;
import com.linktic.test.inventario_service.application.dto.response.CompraResponse;
import com.linktic.test.inventario_service.application.dto.response.HistorialCompraResponse;
import com.linktic.test.inventario_service.application.dto.response.InventarioResponse;
import com.linktic.test.inventario_service.application.usecase.InventarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/inventarios", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario de productos")
public class InventarioController {

    private final InventarioUseCase inventarioUseCase;

    @GetMapping("/cantidad/{productoId}")
    @Operation(summary = "Consultar la cantidad disponible de un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cantidad obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto o inventario no encontrado")
    })
    public Mono<Integer> consultarCantidadDisponible(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable UUID productoId) {
        return inventarioUseCase.consultarCantidadDisponible(productoId);
    }

    @PostMapping("/stock")
    @Operation(summary = "Actualizar el stock de un inventario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Mono<InventarioResponse> actualizarCantidad(
            @Valid @RequestBody StockRequest request) {
        return inventarioUseCase.actualizarCantidad(request.inventarioId(), request.delta())
                .map(InventarioResponse::from);
    }

    @PostMapping("/comprar")
    @Operation(summary = "Realizar una compra de producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Compra realizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o inventario insuficiente"),
            @ApiResponse(responseCode = "404", description = "Producto o inventario no encontrado")
    })
    public Mono<CompraResponse> realizarCompra(
            @Valid @RequestBody CompraRequest request) {
        return inventarioUseCase.realizarCompra(
                        request.productoId(),
                        request.cantidad(),
                        request.observaciones())
                .map(CompraResponse::from);
    }

    @GetMapping("/historial/{inventarioId}")
    @Operation(summary = "Obtener historial de compras")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historial obtenido"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public Flux<HistorialCompraResponse> obtenerHistorial(
            @Parameter(description = "ID del inventario", required = true)
            @PathVariable UUID inventarioId) {
        return inventarioUseCase.obtenerHistorial(inventarioId)
                .map(HistorialCompraResponse::from);
    }

    @PostMapping("/evento/{inventarioId}")
    @Operation(summary = "Emitir evento por cambio de inventario (simulado)")
    public Mono<Void> emitirEventoCambio(
            @Parameter(description = "ID del inventario", required = true)
            @PathVariable UUID inventarioId) {
        return inventarioUseCase.emitirEventoCambio(inventarioId);
    }
}
