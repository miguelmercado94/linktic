package com.linktic.test.productos_service.interfaces.rest;

import com.linktic.test.productos_service.application.dto.request.ProductoRequest;
import com.linktic.test.productos_service.application.dto.response.ProductoResponse;
import com.linktic.test.productos_service.application.usecase.ProductoServiceCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebFluxTest(controllers = ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductoServiceCase productoServiceCase;

    private UUID id;
    private ProductoResponse response;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        response = new ProductoResponse(id, "Mouse", "Gaming", BigDecimal.valueOf(99.99));
    }

    @Test
    void getAll_debeRetornarListaDeProductos() {
        Mockito.when(productoServiceCase.obtenerTodos())
                .thenReturn(Flux.just(response));

        webTestClient.get()
                .uri("/productos")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].nombre").isEqualTo("Mouse")
                .jsonPath("$[0].descripcion").isEqualTo("Gaming");
    }

    @Test
    void getById_debeRetornarProducto() {
        Mockito.when(productoServiceCase.obtenerPorId(id))
                .thenReturn(Mono.just(response));

        webTestClient.get()
                .uri("/productos/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(id.toString())
                .jsonPath("$.nombre").isEqualTo("Mouse");
    }

    @Test
    void create_debeCrearProducto() {
        ProductoRequest request = new ProductoRequest("Teclado", "Mecánico", BigDecimal.valueOf(149.99));
        ProductoResponse creado = new ProductoResponse(UUID.randomUUID(), request.nombre(), request.descripcion(), request.precio());

        Mockito.when(productoServiceCase.crearProducto(any(ProductoRequest.class)))
                .thenReturn(Mono.just(creado));

        webTestClient.post()
                .uri("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Teclado");
    }

    @Test
    void update_debeActualizarProducto() {
        ProductoRequest request = new ProductoRequest("Monitor", "LED", BigDecimal.valueOf(300.00));
        ProductoResponse actualizado = new ProductoResponse(id, request.nombre(), request.descripcion(), request.precio());

        Mockito.when(productoServiceCase.actualizarProducto(eq(id), any(ProductoRequest.class)))
                .thenReturn(Mono.just(actualizado));

        webTestClient.put()
                .uri("/productos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Monitor");
    }
}
