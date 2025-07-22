package com.linktic.test.inventario_service.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class HistorialCompra {
    private final UUID id;
    private final UUID inventarioId;
    private final LocalDateTime fecha;
    private final int cantidadIngresada;
    private final String observaciones;

    public static HistorialCompra registrar(UUID inventarioId, int cantidad, String observaciones) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }

        return HistorialCompra.builder()
                .id(null)
                .inventarioId(inventarioId)
                .fecha(LocalDateTime.now())
                .cantidadIngresada(cantidad)
                .observaciones(observaciones)
                .build();
    }
}
