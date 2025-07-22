package com.linktic.test.productos_service.domain.service;

import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.domain.repository.ProductoRepositoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private ProductoRepositoryDomain repository;
    private ProductoService service;

    private UUID id;
    private Producto producto;

    @BeforeEach
    void setUp() {
        repository = mock(ProductoRepositoryDomain.class);
        service = new ProductoService(repository);

        id = UUID.randomUUID();
        producto = new Producto(id, "Camisa", "Camisa blanca", BigDecimal.valueOf(39.99));
    }

    @Test
    void getProductoByUUID_debeRetornarProducto() {
        when(repository.findById(id)).thenReturn(Mono.just(producto));

        Producto result = service.getProductoByUUID(id).block();

        assertNotNull(result);
        assertEquals("Camisa", result.getNombre());
    }

    @Test
    void getAllProducto_debeRetornarLista() {
        when(repository.findAll()).thenReturn(Flux.just(producto));

        var lista = service.getAllProducto().collectList().block();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    @Test
    void saveNewProducto_debeRetornarProductoGuardado() {
        when(repository.save(producto)).thenReturn(Mono.just(producto));

        Producto result = service.saveNewProducto(producto).block();

        assertNotNull(result);
        assertEquals("Camisa", result.getNombre());
    }

    @Test
    void updateProducto_debeLanzarErrorSiNoTieneId() {
        Producto sinId = new Producto(null, "Nombre", "Desc", BigDecimal.TEN);

        Mono<Producto> result = service.updateProducto(sinId);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalArgumentException);
                    assertEquals("No se puede actualizar un producto sin ID", error.getMessage());
                })
                .verify();
    }


    @Test
    void updateProducto_debeLanzarErrorSiNoExisteEnRepo() {
        when(repository.findById(id)).thenReturn(Mono.empty());

        Producto nuevo = new Producto(id, "Nuevo", "Editado", BigDecimal.ONE);

        Mono<Producto> result = service.updateProducto(nuevo);

        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof RuntimeException);
                    assertTrue(error.getCause() instanceof IllegalStateException);
                    assertTrue(error.getMessage().contains("Error al actualizar producto"));
                })
                .verify();
    }

    @Test
    void updateProducto_debeActualizarCorrectamente() {
        Producto existente = new Producto(id, "Viejo", "Viejo desc", BigDecimal.valueOf(10));
        Producto actualizado = new Producto(id, "Nuevo", "Nuevo desc", BigDecimal.valueOf(20));

        when(repository.findById(id)).thenReturn(Mono.just(existente));
        when(repository.save(any())).thenReturn(Mono.just(actualizado));

        Mono<Producto> result = service.updateProducto(actualizado);

        Producto actualizadoFinal = result.block();
        assertNotNull(actualizadoFinal);
        assertEquals("Nuevo", actualizadoFinal.getNombre());
        assertEquals(BigDecimal.valueOf(20), actualizadoFinal.getPrecio());
    }
}
