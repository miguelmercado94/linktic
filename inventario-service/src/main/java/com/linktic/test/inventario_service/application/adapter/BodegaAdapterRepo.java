package com.linktic.test.inventario_service.application.adapter;

import com.linktic.test.inventario_service.application.mapper.BodegaMapper;
import com.linktic.test.inventario_service.domain.model.Bodega;
import com.linktic.test.inventario_service.domain.repository.BodegaDomainRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.BodegaEntity;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.BodegaR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BodegaAdapterRepo implements BodegaDomainRepository {

    private final BodegaR2dbcRepository repo;
    private final BodegaMapper mapper;

    @Override
    public Mono<Bodega> findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Flux<Bodega> findAll() {
        return repo.findAll()
                .map(mapper::toModel);
    }

    @Override
    public Mono<Bodega> save(Bodega bodega) {
        BodegaEntity entity = mapper.toEntity(bodega);

        UUID id = entity.getId();

        if (id != null) {
            return repo.existsById(id)
                    .flatMap(exists -> {
                        if (exists) {
                            return repo.save(entity)
                                    .map(mapper::toModel);
                        } else {
                            return Mono.error(new IllegalArgumentException("❌ No se puede actualizar: el ID " + id + " no existe en la base"));
                        }
                    });
        } else {
            entity.setId(null);
            return repo.save(entity)
                    .map(mapper::toModel);
        }
    }


    @Override
    public Mono<Void> deleteById(UUID id) {
        return repo.deleteById(id);
    }
}
