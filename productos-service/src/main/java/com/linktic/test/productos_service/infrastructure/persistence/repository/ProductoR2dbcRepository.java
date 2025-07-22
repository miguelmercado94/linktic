package com.linktic.test.productos_service.infrastructure.persistence.repository;

import com.linktic.test.productos_service.infrastructure.persistence.entity.ProductoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoR2dbcRepository extends ReactiveCrudRepository<ProductoEntity, UUID> {
}
