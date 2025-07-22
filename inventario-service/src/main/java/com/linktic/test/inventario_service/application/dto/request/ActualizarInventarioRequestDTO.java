package com.linktic.test.inventario_service.application.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ActualizarInventarioRequestDTO implements Serializable {
    private UUID inventarioId;
    private int cantidad;
    private boolean aumentar;
}
