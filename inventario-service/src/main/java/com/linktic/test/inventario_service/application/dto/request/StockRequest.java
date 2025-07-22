package com.linktic.test.inventario_service.application.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record StockRequest(
        @NotNull UUID inventarioId,
        int delta // Puede ser positivo (aumentar) o negativo (disminuir)
) {}
