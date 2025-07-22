package com.linktic.test.inventario_service.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompraResponse(
        UUID inventarioId,
        int cantidadComprada,
        int cantidadRestante,
        LocalDateTime fecha,
        String observaciones
) {
    public static CompraResponse from(com.linktic.test.inventario_service.application.usecase.InventarioUseCase.CompraResult result) {
        return new CompraResponse(
                result.inventarioId(),
                result.cantidadComprada(),
                result.cantidadRestante(),
                result.fecha(),
                result.observaciones()
        );
    }
}
