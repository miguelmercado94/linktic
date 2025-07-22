package com.linktic.test.inventario_service.application.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductoClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${productos.service.url}")
    private String productoServiceBaseUrl;

    public Mono<Boolean> existeProducto(UUID productoId) {
        return webClientBuilder.build()
                .get()
                .uri(productoServiceBaseUrl + "/productos/{id}", productoId)
                .retrieve()
                .toBodilessEntity()
                .map(response -> true)
                .onErrorResume(e -> Mono.just(false));
    }
}
