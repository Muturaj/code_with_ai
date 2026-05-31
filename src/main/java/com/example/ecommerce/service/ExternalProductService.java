package com.example.ecommerce.service;

import com.example.ecommerce.dto.ExternalProductResponse;
import com.example.ecommerce.exception.ExternalProductException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExternalProductService {

    private final WebClient externalProductsWebClient;

    public ExternalProductService(WebClient externalProductsWebClient) {
        this.externalProductsWebClient = externalProductsWebClient;
    }

    public Mono<List<ExternalProductResponse>> getProducts() {
        return externalProductsWebClient.get()
                .uri("/products")
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("External product API returned an error")
                                .map(ExternalProductException::new)
                )
                .bodyToFlux(ExternalProductResponse.class)
                .collectList()
                .onErrorResume(error -> getDummyProducts());
    }

    public Flux<ExternalProductResponse> streamProducts() {
        return externalProductsWebClient.get()
                .uri("/products")
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .defaultIfEmpty("External product API returned an error")
                                .map(ExternalProductException::new)
                )
                .bodyToFlux(ExternalProductResponse.class);
    }

    public Mono<List<ExternalProductResponse>> getDummyProducts() {
        return Mono.just(List.of(
                new ExternalProductResponse(
                        101,
                        "Dummy Laptop",
                        BigDecimal.valueOf(54999.00),
                        "Dummy ecommerce laptop returned from local fallback data.",
                        "electronics",
                        "https://example.com/images/dummy-laptop.png"
                ),
                new ExternalProductResponse(
                        102,
                        "Dummy Shoes",
                        BigDecimal.valueOf(1999.00),
                        "Dummy running shoes returned from local fallback data.",
                        "fashion",
                        "https://example.com/images/dummy-shoes.png"
                ),
                new ExternalProductResponse(
                        103,
                        "Dummy Watch",
                        BigDecimal.valueOf(2999.00),
                        "Dummy smart watch returned from local fallback data.",
                        "accessories",
                        "https://example.com/images/dummy-watch.png"
                )
        ));
    }
}
