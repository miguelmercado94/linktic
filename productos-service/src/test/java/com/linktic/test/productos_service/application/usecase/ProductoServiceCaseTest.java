package com.linktic.test.productos_service.application.usecase;

import com.linktic.test.productos_service.application.dto.request.ProductoRequest;
import com.linktic.test.productos_service.application.dto.response.ProductoResponse;
import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.domain.service.IProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceCaseTest {

    private IProductoService productoService;
    private ProductoServiceCase productoCase;

    @BeforeEach
    void setUp() {
        productoService = mock(IProductoService.class);
        productoCase = new ProductoServiceCase(productoService);
    }

    @Test
    void obtenerTodos_debeRetornarListaDeProductos() {
        Producto p1 = new Producto(UUID.randomUUID(), "Producto 1", "Desc 1", BigDecimal.TEN);
        Producto p2 = new Producto(UUID.randomUUID(), "Producto 2", "Desc 2", BigDecimal.ONE);

        when(productoService.getAllProducto()).thenReturn(Flux.just(p1, p2));

        List<ProductoResponse> result = productoCase.obtenerTodos().collectList().block();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Producto 1", result.get(0).nombre());
    }

    @Test
    void obtenerPorId_debeRetornarProductoSiExiste() {
        UUID id = UUID.randomUUID();
        Producto p = new Producto(id, "Mouse", "Pequeño", BigDecimal.valueOf(99));

        when(productoService.getProductoByUUID(id)).thenReturn(Mono.just(p));

        ProductoResponse response = productoCase.obtenerPorId(id).block();

        assertNotNull(response);
        assertEquals("Mouse", response.nombre());
        assertEquals(id, response.id());
    }

    @Test
    void crearProducto_debeRetornarProductoGuardado() {
        ProductoRequest request = new ProductoRequest("Teclado", "Mecánico", BigDecimal.valueOf(120));
        Producto saved = new Producto(UUID.randomUUID(), request.nombre(), request.descripcion(), request.precio());

        when(productoService.saveNewProducto(any(Producto.class))).thenReturn(Mono.just(saved));

        ProductoResponse response = productoCase.crearProducto(request).block();

        assertNotNull(response);
        assertEquals("Teclado", response.nombre());
    }

    @Test
    void actualizarProducto_debeActualizarYRetornarProducto() {
        UUID id = UUID.randomUUID();
        ProductoRequest request = new ProductoRequest("Monitor", "LED", BigDecimal.valueOf(400));
        Producto updated = new Producto(id, request.nombre(), request.descripcion(), request.precio());

        when(productoService.updateProducto(any(Producto.class))).thenReturn(Mono.just(updated));

        ProductoResponse response = productoCase.actualizarProducto(id, request).block();

        assertNotNull(response);
        assertEquals("Monitor", response.nombre());
        assertEquals(id, response.id());
    }
}
