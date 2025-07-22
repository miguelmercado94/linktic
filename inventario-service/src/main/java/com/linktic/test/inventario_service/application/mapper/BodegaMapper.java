package com.linktic.test.inventario_service.application.mapper;

import com.linktic.test.inventario_service.domain.model.Bodega;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.BodegaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BodegaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "direccion", source = "direccion")
    Bodega toModel(BodegaEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "direccion", source = "direccion")
    BodegaEntity toEntity(Bodega model);
}
