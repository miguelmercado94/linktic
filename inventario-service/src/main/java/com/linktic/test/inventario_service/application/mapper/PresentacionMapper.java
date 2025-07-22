package com.linktic.test.inventario_service.application.mapper;

import com.linktic.test.inventario_service.domain.model.Presentacion;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.PresentacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PresentacionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productoId", source = "productoId")
    @Mapping(target = "unidadMedida", source = "unidadMedida")
    @Mapping(target = "cantidadPorEmpaque", source = "cantidadPorEmpaque")
    @Mapping(target = "cantidadEmpaques", source = "cantidadEmpaques")
    @Mapping(target = "descripcion", source = "descripcion")
    Presentacion toModel(PresentacionEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productoId", source = "productoId")
    @Mapping(target = "unidadMedida", source = "unidadMedida")
    @Mapping(target = "cantidadPorEmpaque", source = "cantidadPorEmpaque")
    @Mapping(target = "cantidadEmpaques", source = "cantidadEmpaques")
    @Mapping(target = "descripcion", source = "descripcion")
    PresentacionEntity toEntity(Presentacion model);
}
