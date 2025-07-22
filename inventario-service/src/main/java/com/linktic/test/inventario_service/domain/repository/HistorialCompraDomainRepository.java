package com.linktic.test.inventario_service.domain.repository;

import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface HistorialCompraDomainRepository {
    Mono<HistorialCompra> save(HistorialCompra historial);
    Flux<HistorialCompra> findByInventarioId(UUID inventarioId);
}
