package com.linktic.test.inventario_service.application.dto.response;

import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import com.linktic.test.inventario_service.domain.model.Presentacion;
import com.linktic.test.inventario_service.domain.model.UnidadMedida;

import java.util.UUID;

public record InventarioResponse(
        UUID inventarioId,
        UUID bodegaId,
        String bodegaNombre,
        String bodegaDireccion,
        UUID productoId,
        UnidadMedida unidadMedida,
        int cantidadPorEmpaque,
        int cantidadEmpaques,
        String descripcionPresentacion,
        int cantidadDisponible
) {
    public static InventarioResponse from(InventarioAggregate inventario) {
        Presentacion p = inventario.getPresentacion();
        return new InventarioResponse(
                inventario.getId(),
                inventario.getBodega().getId(),
                inventario.getBodega().getNombre(),
                inventario.getBodega().getDireccion(),
                p.getProductoId(),
                p.getUnidadMedida(),
                p.getCantidadPorEmpaque(),
                p.getCantidadEmpaques(),
                p.getDescripcion(),
                inventario.getCantidad()
        );
    }
}
