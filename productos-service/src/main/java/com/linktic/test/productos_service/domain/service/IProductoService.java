package com.linktic.test.productos_service.domain.service;

import com.linktic.test.productos_service.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IProductoService {

    Mono<Producto> getProductoByUUID(UUID id);

    Flux<Producto> getAllProducto();

    Mono<Producto> saveNewProducto(Producto producto);

    Mono<Producto> updateProducto(Producto producto);

}
