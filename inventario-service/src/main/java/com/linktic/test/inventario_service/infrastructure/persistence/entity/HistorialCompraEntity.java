package com.linktic.test.inventario_service.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("historial_compra")
public class HistorialCompraEntity {
    @Id
    private UUID id;

    @Column("inventario_id")
    private UUID inventarioId;

    private LocalDateTime fecha;

    @Column("cantidad_ingresada")
    private int cantidadIngresada;

    private String observaciones;
}
