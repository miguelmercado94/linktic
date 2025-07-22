package com.linktic.test.productos_service.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Respuesta con la información completa de un producto")
public record ProductoResponse(

        @Schema(description = "Identificador único del producto", example = "e3f6cdd0-91f6-4d98-8cf1-392e558efff6")
        UUID id,

        @Schema(description = "Nombre del producto", example = "Camisa Polo")
        String nombre,

        @Schema(description = "Descripción detallada del producto", example = "Camisa Polo de algodón para hombre")
        String descripcion,

        @Schema(description = "Precio del producto", example = "59.99")
        BigDecimal precio

) {}
