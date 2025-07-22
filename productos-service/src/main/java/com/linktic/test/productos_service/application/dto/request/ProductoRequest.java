package com.linktic.test.productos_service.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Datos para crear o actualizar un producto")
public record ProductoRequest(

        @Schema(description = "Nombre del producto", example = "Camisa Polo", required = true)
        @NotBlank
        String nombre,

        @Schema(description = "Descripción detallada del producto", example = "Camisa Polo de algodón para hombre", required = true)
        @NotBlank
        String descripcion,

        @Schema(description = "Precio del producto en moneda local", example = "59.99", required = true)
        @NotNull
        BigDecimal precio

) {}
