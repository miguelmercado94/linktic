package com.linktic.test.inventario_service.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompraRequest(
        @NotNull UUID productoId,
        @Min(1) int cantidad,
        String observaciones
) {}
