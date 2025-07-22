package com.linktic.test.inventario_service.application.adapter;

import com.linktic.test.inventario_service.application.mapper.HistorialCompraMapper;
import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import com.linktic.test.inventario_service.domain.repository.HistorialCompraDomainRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.HistorialCompraEntity;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.HistorialCompraR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HistorialCompraAdapterRepo implements HistorialCompraDomainRepository {

    private final HistorialCompraR2dbcRepository repo;
    private final HistorialCompraMapper mapper;

    @Override
    public Mono<HistorialCompra> save(HistorialCompra historial) {
        HistorialCompraEntity entity = mapper.toEntity(historial);

        if (entity.getId() != null) {
            return repo.existsById(entity.getId())
                    .flatMap(exists -> {
                        if (exists) {
                            return repo.save(entity)
                                    .map(mapper::toModel);
                        } else {
                            return Mono.error(new IllegalArgumentException("❌ No se puede actualizar: el ID " + entity.getId() + " no existe"));
                        }
                    });
        } else {
            return repo.save(entity)
                    .map(mapper::toModel);
        }
    }



    @Override
    public Flux<HistorialCompra> findByInventarioId(UUID inventarioId) {
        return repo.findByInventarioId(inventarioId)
                .map(mapper::toModel);
    }
}
