package com.linktic.test.inventario_service.domain.service.impl;

import com.linktic.test.inventario_service.application.adapter.PresentacionAdapterRepo;
import com.linktic.test.inventario_service.domain.model.Presentacion;
import com.linktic.test.inventario_service.domain.service.IPresentacionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PresentacionDomainServiceImpl implements IPresentacionDomainService {

    private final PresentacionAdapterRepo presentacionAdapter;

    @Override
    public Mono<Presentacion> findById(UUID id) {
        return presentacionAdapter.findById(id);
    }

    @Override
    public Flux<Presentacion> findAll() {
        return presentacionAdapter.findAll();
    }

    @Override
    public Mono<Presentacion> save(Presentacion presentacion) {
        return presentacionAdapter.save(presentacion);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return presentacionAdapter.deleteById(id);
    }
}
