package com.linktic.test.inventario_service.domain.repository;

import com.linktic.test.inventario_service.domain.model.Bodega;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BodegaDomainRepository {
    Mono<Bodega> findById(UUID id);
    Flux<Bodega> findAll();
    Mono<Bodega> save(Bodega bodega);
    Mono<Void> deleteById(UUID id);
}
