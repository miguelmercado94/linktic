package com.linktic.test.inventario_service.infrastructure.persistence.repository;

import com.linktic.test.inventario_service.infrastructure.persistence.entity.InventarioEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface InventarioR2dbcRepository extends ReactiveCrudRepository<InventarioEntity, UUID> {

    @Query("""
    SELECT i.id, i.bodega_id, i.presentacion_id, i.cantidad
    FROM inventario i
    JOIN presentacion p ON p.id = i.presentacion_id
    WHERE p.producto_id = :productoId
""")
    Flux<InventarioEntity> findByProductoId(UUID productoId);

}
