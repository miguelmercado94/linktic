package com.linktic.test.inventario_service.domain.service;

import com.linktic.test.inventario_service.domain.model.Presentacion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IPresentacionDomainService {
    Mono<Presentacion> findById(UUID id);
    Flux<Presentacion> findAll();
    Mono<Presentacion> save(Presentacion presentacion);
    Mono<Void> deleteById(UUID id);
}
