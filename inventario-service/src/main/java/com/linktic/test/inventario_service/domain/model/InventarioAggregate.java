package com.linktic.test.inventario_service.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter

@Builder(toBuilder = true)
public class InventarioAggregate {
    private final UUID id;
    private final Bodega bodega;
    private final Presentacion presentacion;
    private final int cantidad;

    public InventarioAggregate agregar(int unidades) {
        if (unidades <= 0) {
            throw new IllegalArgumentException("Unidades a agregar deben ser mayores a cero");
        }
        return this.toBuilder().cantidad(this.cantidad + unidades).build();
    }

    public InventarioAggregate descontar(int unidades) {
        if (unidades <= 0 || unidades > cantidad) {
            throw new IllegalArgumentException("Unidades inválidas para descontar");
        }
        return this.toBuilder().cantidad(this.cantidad - unidades).build();
    }
}
