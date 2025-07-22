package com.linktic.test.inventario_service.domain.service.impl;

import com.linktic.test.inventario_service.application.adapter.InventarioAdapterRepo;
import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import com.linktic.test.inventario_service.domain.service.IInventarioDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventarioDomainServiceImpl implements IInventarioDomainService {

    private final InventarioAdapterRepo inventarioAdapter;

    @Override
    public Mono<InventarioAggregate> aumentarCantidad(UUID inventarioId, int cantidad) {
        return inventarioAdapter.findById(inventarioId)
                .map(inventario -> inventario.agregar(cantidad))
                .flatMap(inventarioAdapter::save);
    }

    @Override
    public Mono<InventarioAggregate> disminuirCantidad(UUID inventarioId, int cantidad) {
        return inventarioAdapter.findById(inventarioId)
                .map(inventario -> inventario.descontar(cantidad))
                .flatMap(inventarioAdapter::save);
    }

    @Override
    public Flux<InventarioAggregate> findByProductoId(UUID productoId) {
        return inventarioAdapter.findByProductoId(productoId);
    }

    @Override
    public Mono<InventarioAggregate> findById(UUID id) {
        return inventarioAdapter.findById(id);
    }

    @Override
    public Mono<Void> eliminarInventario(UUID id) {
        return inventarioAdapter.deleteById(id);
    }

    @Override
    public Mono<InventarioAggregate> buscarPorProductoId(UUID productoId) {
        return inventarioAdapter.findByProductoId(productoId).next();
    }
}
