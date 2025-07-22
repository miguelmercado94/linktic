package com.linktic.test.inventario_service.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("inventario")
public class InventarioEntity {
    @Id
    private UUID id;

    @Column("bodega_id")
    private UUID bodegaId;

    @Column("presentacion_id")
    private UUID presentacionId;

    private int cantidad;
}
