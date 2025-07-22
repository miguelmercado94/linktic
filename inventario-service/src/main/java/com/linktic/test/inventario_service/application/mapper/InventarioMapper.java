package com.linktic.test.inventario_service.application.mapper;

import com.linktic.test.inventario_service.infrastructure.persistence.entity.BodegaEntity;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.InventarioEntity;
import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import com.linktic.test.inventario_service.domain.model.Presentacion;
import com.linktic.test.inventario_service.domain.model.Bodega;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.PresentacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventarioMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "cantidad", source = "entity.cantidad")
    @Mapping(target = "bodega", source = "bodega")
    @Mapping(target = "presentacion", source = "presentacion")
    InventarioAggregate toAggregate(InventarioEntity entity, Bodega bodega, Presentacion presentacion);

    @Mapping(target = "bodegaId", source = "bodega.id")
    @Mapping(target = "presentacionId", source = "presentacion.id")
    @Mapping(target = "id", source = "aggregate.id")
    @Mapping(target = "cantidad", source = "aggregate.cantidad")
    InventarioEntity toEntity(InventarioAggregate aggregate);

}
