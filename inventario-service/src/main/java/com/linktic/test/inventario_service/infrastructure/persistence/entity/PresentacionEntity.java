package com.linktic.test.inventario_service.infrastructure.persistence.entity;

import com.linktic.test.inventario_service.domain.model.UnidadMedida;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("presentacion")
public class PresentacionEntity {
    @Id
    private UUID id;

    @Column("producto_id")
    private UUID productoId;

    @Column("unidad_medida")
    private UnidadMedida unidadMedida;

    @Column("cantidad_por_empaque")
    private int cantidadPorEmpaque;

    @Column("cantidad_empaques")
    private int cantidadEmpaques;

    private String descripcion;
}
