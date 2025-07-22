package com.linktic.test.productos_service.interfaces.rest;

import com.linktic.test.productos_service.application.dto.request.ProductoRequest;
import com.linktic.test.productos_service.application.dto.response.ProductoResponse;
import com.linktic.test.productos_service.application.usecase.ProductoServiceCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controlador REST para operaciones sobre productos.
 */
@RestController
@RequestMapping(value = "/productos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServiceCase productoCase;

    /**
     * Obtener todos los productos disponibles.
     *
     * @return Una lista reactiva de productos.
     */
    @Operation(summary = "Listar todos los productos", description = "Retorna todos los productos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public Flux<ProductoResponse> getAll() {
        return productoCase.obtenerTodos();
    }

    /**
     * Obtener un producto por su UUID.
     *
     * @param id Identificador UUID del producto.
     * @return Producto encontrado o error 404 si no existe.
     */
    @Operation(summary = "Buscar producto por ID", description = "Devuelve un producto por su UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public Mono<ProductoResponse> getById(
            @Parameter(description = "UUID del producto", required = true)
            @PathVariable UUID id) {
        return productoCase.obtenerPorId(id);
    }

    /**
     * Crear un nuevo producto.
     *
     * @param request Datos del nuevo producto.
     * @return Producto creado.
     */
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto con los datos proporcionados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductoResponse> create(
            @Parameter(description = "Datos del nuevo producto", required = true)
            @Valid @RequestBody ProductoRequest request) {
        return productoCase.crearProducto(request);
    }

    /**
     * Actualizar un producto existente.
     *
     * @param id      UUID del producto a actualizar.
     * @param request Nuevos datos del producto.
     * @return Producto actualizado.
     */
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error de validación")
    })
    @PutMapping("/{id}")
    public Mono<ProductoResponse> update(
            @Parameter(description = "UUID del producto a actualizar", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Nuevos datos del producto", required = true)
            @Valid @RequestBody ProductoRequest request) {
        return productoCase.actualizarProducto(id, request);
    }
}
