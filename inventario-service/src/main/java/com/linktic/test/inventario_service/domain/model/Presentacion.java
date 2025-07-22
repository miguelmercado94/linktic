package com.linktic.test.inventario_service.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Presentacion {
    private final UUID id;
    private final UUID productoId;
    private final UnidadMedida unidadMedida;
    private final int cantidadPorEmpaque;
    private final int cantidadEmpaques;
    private final String descripcion;

    public static Presentacion crear(UUID productoId, UnidadMedida unidad, int porEmpaque, int empaques, String descripcion) {
        if (porEmpaque <= 0 || empaques <= 0) {
            throw new IllegalArgumentException("Valores por empaque y empaques deben ser mayores a cero");
        }

        return Presentacion.builder()
                .id(UUID.randomUUID())
                .productoId(productoId)
                .unidadMedida(unidad)
                .cantidadPorEmpaque(porEmpaque)
                .cantidadEmpaques(empaques)
                .descripcion(descripcion)
                .build();
    }
}
