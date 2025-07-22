package com.linktic.test.productos_service.infrastructure.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("productos")
public class ProductoEntity {

    @Id
    private UUID id;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
}
