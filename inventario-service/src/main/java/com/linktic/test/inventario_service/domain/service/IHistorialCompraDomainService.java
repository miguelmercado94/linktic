package com.linktic.test.inventario_service.domain.service;

import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IHistorialCompraDomainService {
    Mono<HistorialCompra> registrarCompra(UUID inventarioId, int cantidad, String observaciones);
    Flux<HistorialCompra> findByInventarioId(UUID inventarioId);
}
