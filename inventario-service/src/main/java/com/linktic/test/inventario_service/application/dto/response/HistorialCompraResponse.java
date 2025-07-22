package com.linktic.test.inventario_service.application.dto.response;

import com.linktic.test.inventario_service.domain.model.HistorialCompra;

import java.time.LocalDateTime;
import java.util.UUID;

public record HistorialCompraResponse(
        UUID id,
        UUID inventarioId,
        LocalDateTime fecha,
        int cantidadIngresada,
        String observaciones
) {
    public static HistorialCompraResponse from(HistorialCompra compra) {
        return new HistorialCompraResponse(
                compra.getId(),
                compra.getInventarioId(),
                compra.getFecha(),
                compra.getCantidadIngresada(),
                compra.getObservaciones()
        );
    }
}
