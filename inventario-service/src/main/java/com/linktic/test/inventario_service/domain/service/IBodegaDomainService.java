package com.linktic.test.inventario_service.domain.service;

import com.linktic.test.inventario_service.domain.model.Bodega;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IBodegaDomainService {
    Mono<Bodega> findById(UUID id);
    Flux<Bodega> findAll();
    Mono<Bodega> save(Bodega bodega);
    Mono<Void> deleteById(UUID id);
}