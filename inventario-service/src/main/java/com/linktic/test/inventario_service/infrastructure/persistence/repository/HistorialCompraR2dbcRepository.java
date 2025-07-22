package com.linktic.test.inventario_service.infrastructure.persistence.repository;

import com.linktic.test.inventario_service.infrastructure.persistence.entity.HistorialCompraEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface HistorialCompraR2dbcRepository extends ReactiveCrudRepository<HistorialCompraEntity, UUID> {

    @Query("""
        SELECT * FROM historial_compra
        WHERE inventario_id = :inventarioId
    """)
    Flux<HistorialCompraEntity> findByInventarioId(UUID inventarioId);

}
