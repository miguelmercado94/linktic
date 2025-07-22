package com.linktic.test.inventario_service.domain.service.impl;

import com.linktic.test.inventario_service.application.adapter.HistorialCompraAdapterRepo;
import com.linktic.test.inventario_service.domain.model.HistorialCompra;
import com.linktic.test.inventario_service.domain.service.IHistorialCompraDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistorialCompraDomainServiceImpl implements IHistorialCompraDomainService {

    private final HistorialCompraAdapterRepo historialAdapter;

    @Override
    public Mono<HistorialCompra> registrarCompra(UUID inventarioId, int cantidad, String observaciones) {
        HistorialCompra historial = HistorialCompra.registrar(inventarioId, cantidad, observaciones);
        return historialAdapter.save(historial);
    }

    @Override
    public Flux<HistorialCompra> findByInventarioId(UUID inventarioId) {
        return historialAdapter.findByInventarioId(inventarioId);
    }
}
