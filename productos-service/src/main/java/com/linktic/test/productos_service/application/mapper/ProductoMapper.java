package com.linktic.test.productos_service.application.mapper;

import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.infrastructure.persistence.entity.ProductoEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface  ProductoMapper {

    Producto toModel(ProductoEntity entity);

    ProductoEntity toEntity(Producto model);

}
