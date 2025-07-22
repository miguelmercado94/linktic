package com.linktic.test.inventario_service.application.adapter;

import com.linktic.test.inventario_service.application.mapper.PresentacionMapper;
import com.linktic.test.inventario_service.domain.model.Presentacion;
import com.linktic.test.inventario_service.domain.repository.PresentacionDomainRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.PresentacionEntity;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.PresentacionR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PresentacionAdapterRepo implements PresentacionDomainRepository {

    private final PresentacionR2dbcRepository repo;
    private final PresentacionMapper mapper;

    @Override
    public Mono<Presentacion> findById(UUID id) {
        return repo.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Flux<Presentacion> findAll() {
        return repo.findAll()
                .map(mapper::toModel);
    }

    @Override
    public Mono<Presentacion> save(Presentacion presentacion) {
        PresentacionEntity entity = mapper.toEntity(presentacion);
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
