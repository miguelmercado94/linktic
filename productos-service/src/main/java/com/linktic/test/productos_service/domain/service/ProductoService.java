package com.linktic.test.productos_service.domain.service;

import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.domain.repository.ProductoRepositoryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final ProductoRepositoryDomain productoRepositoryDomain;

    @Override
    public Mono<Producto> getProductoByUUID(UUID id) {
        return productoRepositoryDomain.findById(id);
    }

    @Override
    public Flux<Producto> getAllProducto() {
        return productoRepositoryDomain.findAll();
    }

    @Override
    public Mono<Producto> saveNewProducto(Producto producto) {
        return productoRepositoryDomain.save(producto);
    }

    @Override
    public Mono<Producto> updateProducto(Producto producto) {
        if (producto.getId() == null) {
            return Mono.error(new IllegalArgumentException(
                    "No se puede actualizar un producto sin ID"));
        }

        return productoRepositoryDomain.findById(producto.getId())
                .switchIfEmpty(Mono.error(new IllegalStateException(
                        "Producto no encontrado con ID: " + producto.getId())))
                .flatMap(existing -> {
                    existing.setNombre(producto.getNombre());
                    existing.setDescripcion(producto.getDescripcion());
                    existing.setPrecio(producto.getPrecio());

                    return productoRepositoryDomain.save(existing)
                            .onErrorMap(e -> new RuntimeException(
                                    "Error al guardar producto actualizado", e));
                })
                .onErrorMap(e -> new RuntimeException(
                        "Error al actualizar producto", e));
    }
}
