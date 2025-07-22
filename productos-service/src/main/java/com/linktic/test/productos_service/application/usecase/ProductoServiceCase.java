package com.linktic.test.productos_service.application.usecase;

import com.linktic.test.productos_service.application.dto.request.ProductoRequest;
import com.linktic.test.productos_service.application.dto.response.ProductoResponse;
import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.domain.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceCase {

    private final IProductoService productoService;

    public Flux<ProductoResponse> obtenerTodos() {
        return productoService.getAllProducto()
                .map(this::toResponse);
    }

    public Mono<ProductoResponse> obtenerPorId(UUID id) {
        return productoService.getProductoByUUID(id)
                .map(this::toResponse);
    }

    public Mono<ProductoResponse> crearProducto(ProductoRequest request) {
        Producto producto = new Producto(null, request.nombre(), request.descripcion(), request.precio());
        return productoService.saveNewProducto(producto)
                .map(this::toResponse);
    }

    public Mono<ProductoResponse> actualizarProducto(UUID id, ProductoRequest request) {
        Producto producto = new Producto(id, request.nombre(), request.descripcion(), request.precio());
        return productoService.updateProducto(producto)
                .map(this::toResponse);
    }

    private ProductoResponse toResponse(Producto p) {
        return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio());
    }
}
