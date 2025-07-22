package com.linktic.test.productos_service.application.adapter;

import com.linktic.test.productos_service.application.mapper.ProductoMapper;
import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.domain.repository.ProductoRepositoryDomain;
import com.linktic.test.productos_service.infrastructure.persistence.entity.ProductoEntity;
import com.linktic.test.productos_service.infrastructure.persistence.repository.ProductoR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductoAdapterRepo implements ProductoRepositoryDomain {

    private final ProductoR2dbcRepository repoPersistencia;
    private final ProductoMapper mapper;

    @Override
    public Mono<Producto> findById(UUID id) {
        return repoPersistencia.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Flux<Producto> findAll() {
        return repoPersistencia.findAll()
                .map(mapper::toModel);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        ProductoEntity entity = mapper.toEntity(producto);
        UUID id = entity.getId();

        if (id != null) {
            return repoPersistencia.existsById(id)
                    .flatMap(exists -> {
                        if (exists) {
                            // ✅ Actualización
                            return repoPersistencia.save(entity)
                                    .map(mapper::toModel);
                        } else {
                            // ❌ Error si el ID no está en la base
                            return Mono.error(new IllegalArgumentException("❌ No se puede actualizar: el ID " + id + " no existe en la base"));
                        }
                    });
        } else {
            entity.setId(null);
            return repoPersistencia.save(entity)
                    .map(mapper::toModel);
        }
    }

}
