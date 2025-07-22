package com.linktic.test.inventario_service.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Bodega {
    private final UUID id;
    private final String nombre;
    private final String direccion;
}
