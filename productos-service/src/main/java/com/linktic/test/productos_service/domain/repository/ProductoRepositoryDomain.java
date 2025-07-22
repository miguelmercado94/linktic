package com.linktic.test.productos_service.domain.repository;

import com.linktic.test.productos_service.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductoRepositoryDomain {

    public Mono<Producto> findById(UUID id);

    public Flux<Producto> findAll();

    public Mono<Producto> save(Producto producto);
}
