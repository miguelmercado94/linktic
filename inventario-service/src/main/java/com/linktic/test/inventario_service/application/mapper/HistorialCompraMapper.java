package com.linktic.test.inventario_service.application.mapper;

import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.HistorialCompraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistorialCompraMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "inventarioId", source = "inventarioId")
    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "cantidadIngresada", source = "cantidadIngresada")
    @Mapping(target = "observaciones", source = "observaciones")
    HistorialCompra toModel(HistorialCompraEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "inventarioId", source = "inventarioId")
    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "cantidadIngresada", source = "cantidadIngresada")
    @Mapping(target = "observaciones", source = "observaciones")
    HistorialCompraEntity toEntity(HistorialCompra model);
}
