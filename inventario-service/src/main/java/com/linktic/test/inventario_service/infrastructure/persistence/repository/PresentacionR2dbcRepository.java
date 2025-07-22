package com.linktic.test.inventario_service.infrastructure.persistence.repository;

import com.linktic.test.inventario_service.infrastructure.persistence.entity.PresentacionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PresentacionR2dbcRepository extends ReactiveCrudRepository<PresentacionEntity, UUID> {
}
