package com.linktic.test.inventario_service.domain.service;

import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface IInventarioDomainService {
    Mono<InventarioAggregate> aumentarCantidad(UUID inventarioId, int cantidad);
    Mono<InventarioAggregate> disminuirCantidad(UUID inventarioId, int cantidad);
    Flux<InventarioAggregate> findByProductoId(UUID productoId);
    Mono<InventarioAggregate> findById(UUID id);
    Mono<Void> eliminarInventario(UUID id);
    Mono<InventarioAggregate> buscarPorProductoId(UUID productoId);
}
