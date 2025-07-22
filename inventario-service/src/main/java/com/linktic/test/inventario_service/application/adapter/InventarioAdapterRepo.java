package com.linktic.test.inventario_service.application.adapter;

import com.linktic.test.inventario_service.application.mapper.BodegaMapper;
import com.linktic.test.inventario_service.application.mapper.InventarioMapper;
import com.linktic.test.inventario_service.application.mapper.PresentacionMapper;
import com.linktic.test.inventario_service.domain.model.InventarioAggregate;
import com.linktic.test.inventario_service.domain.repository.InventarioDomainRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.entity.InventarioEntity;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.BodegaR2dbcRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.InventarioR2dbcRepository;
import com.linktic.test.inventario_service.infrastructure.persistence.repository.PresentacionR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventarioAdapterRepo implements InventarioDomainRepository {

    private final InventarioR2dbcRepository inventarioRepo;
    private final BodegaR2dbcRepository bodegaRepo;
    private final PresentacionR2dbcRepository presentacionRepo;

    private final InventarioMapper inventarioMapper;
    private final BodegaMapper bodegaMapper;
    private final PresentacionMapper presentacionMapper;

    @Override
    public Flux<InventarioAggregate> findByProductoId(UUID productoId) {
        inventarioRepo.findByProductoId(productoId)
                .hasElements()
                .subscribe(has -> System.out.println("📦 ¿Tiene inventario? " + has));

        return inventarioRepo.findByProductoId(productoId)
                .flatMap(this::mapToAggregate);
    }

    @Override
    public Mono<InventarioAggregate> findById(UUID id) {
        return inventarioRepo.findById(id)
                .flatMap(this::mapToAggregate);
    }

    @Override
    public Mono<InventarioAggregate> save(InventarioAggregate inventario) {
        InventarioEntity entity = inventarioMapper.toEntity(inventario);
        UUID id = entity.getId();

        if (id != null) {
            // Verificamos existencia del ID antes de actualizar
            return inventarioRepo.existsById(id)
                    .flatMap(exists -> {
                        if (exists) {
                            return inventarioRepo.save(entity)
                                    .flatMap(this::mapToAggregate);
                        } else {
                            return Mono.error(new IllegalArgumentException("❌ No se puede actualizar: el ID " + id + " no existe en la base"));
                        }
                    });
        } else {
            entity.setId(null);
            return inventarioRepo.save(entity)
                    .flatMap(this::mapToAggregate);
        }
    }


    @Override
    public Mono<Void> deleteById(UUID id) {
        return inventarioRepo.deleteById(id);
    }

    private Mono<InventarioAggregate> mapToAggregate(InventarioEntity entity) {
        return Mono.zip(
                bodegaRepo.findById(entity.getBodegaId())
                        .doOnNext(b -> System.out.println("✅ BodegaEntity encontrada: " + b))
                        .map(bodegaMapper::toModel),
                presentacionRepo.findById(entity.getPresentacionId())
                        .doOnNext(p -> System.out.println("✅ PresentacionEntity encontrada: " + p))
                        .map(presentacionMapper::toModel)
        ).map(tuple -> {
            System.out.println("🎯 Mapeando InventarioAggregate");
            return inventarioMapper.toAggregate(entity, tuple.getT1(), tuple.getT2());
        });

    }

}
