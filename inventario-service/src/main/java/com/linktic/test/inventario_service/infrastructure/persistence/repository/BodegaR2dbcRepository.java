package com.linktic.test.inventario_service.infrastructure.persistence.repository;

import com.linktic.test.inventario_service.infrastructure.persistence.entity.BodegaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface BodegaR2dbcRepository extends ReactiveCrudRepository<BodegaEntity, UUID> {
}
