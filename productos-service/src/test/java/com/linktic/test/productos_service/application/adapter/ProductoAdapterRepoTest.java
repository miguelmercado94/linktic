package com.linktic.test.productos_service.application.adapter;

import com.linktic.test.productos_service.application.mapper.ProductoMapper;
import com.linktic.test.productos_service.domain.model.Producto;
import com.linktic.test.productos_service.infrastructure.persistence.entity.ProductoEntity;
import com.linktic.test.productos_service.infrastructure.persistence.repository.ProductoR2dbcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoAdapterRepoTest {

    private ProductoR2dbcRepository repo;
    private ProductoMapper mapper;
    private ProductoAdapterRepo adapter;

    private UUID id;
    private Producto producto;
    private ProductoEntity entity;

    @BeforeEach
    void setUp() {
        repo = mock(ProductoR2dbcRepository.class);
        mapper = mock(ProductoMapper.class);
        adapter = new ProductoAdapterRepo(repo, mapper);

        id = UUID.randomUUID();
        producto = new Producto(id, "TV", "Smart TV", BigDecimal.valueOf(2999.99));
        entity = new ProductoEntity(id, "TV", "Smart TV", BigDecimal.valueOf(2999.99));
    }

    @Test
    void findById_debeRetornarProducto() {
        when(repo.findById(id)).thenReturn(Mono.just(entity));
        when(mapper.toModel(entity)).thenReturn(producto);

        Producto result = adapter.findById(id).block();

        assertNotNull(result);
        assertEquals("TV", result.getNombre());
    }

    @Test
    void findAll_debeRetornarListaProductos() {
        when(repo.findAll()).thenReturn(Flux.just(entity));
        when(mapper.toModel(entity)).thenReturn(producto);

        var list = adapter.findAll().collectList().block();

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("TV", list.get(0).getNombre());
    }

    @Test
    void save_debeGuardarYRetornarProducto() {
        when(mapper.toEntity(producto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.toModel(entity)).thenReturn(producto);

        Producto result = adapter.save(producto).block();

        assertNotNull(result);
        assertEquals("TV", result.getNombre());
    }
}
