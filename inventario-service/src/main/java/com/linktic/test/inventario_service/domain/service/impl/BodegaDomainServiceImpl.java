package com.linktic.test.inventario_service.domain.service.impl;

import com.linktic.test.inventario_service.application.adapter.BodegaAdapterRepo;
import com.linktic.test.inventario_service.domain.model.Bodega;
import com.linktic.test.inventario_service.domain.service.IBodegaDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BodegaDomainServiceImpl implements IBodegaDomainService {

    private final BodegaAdapterRepo bodegaAdapter;

    @Override
    public Mono<Bodega> findById(UUID id) {
        return bodegaAdapter.findById(id);
    }

    @Override
    public Flux<Bodega> findAll() {
        return bodegaAdapter.findAll();
    }

    @Override
    public Mono<Bodega> save(Bodega bodega) {
        return bodegaAdapter.save(bodega);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return bodegaAdapter.deleteById(id);
    }
}
