package com.linktic.test.inventario_service.domain.repository;

import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InventarioDomainRepository {

    Flux<InventarioAggregate> findByProductoId(UUID productoId);

    Mono<InventarioAggregate> findById(UUID inventarioId);

    Mono<InventarioAggregate> save(InventarioAggregate inventario);

    Mono<Void> deleteById(UUID inventarioId);
}
