package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ExternalProductResponse;
import com.example.ecommerce.service.ExternalProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/external/products")
@Tag(name = "External Products", description = "Reactive WebClient calls to an external product API")
public class ExternalProductController {

    private final ExternalProductService externalProductService;

    public ExternalProductController(ExternalProductService externalProductService) {
        this.externalProductService = externalProductService;
    }

    @GetMapping
    @Operation(summary = "Fetch products from the external Fake Store API using WebClient, with dummy fallback data")
    public Mono<List<ExternalProductResponse>> getExternalProducts() {
        return externalProductService.getProducts();
    }

    @GetMapping("/dummy")
    @Operation(summary = "Get dummy external products without calling the real external host")
    public Mono<List<ExternalProductResponse>> getDummyExternalProducts() {
        return externalProductService.getDummyProducts();
    }

    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    @Operation(summary = "Stream products from the external Fake Store API as NDJSON")
    public Flux<ExternalProductResponse> streamExternalProducts() {
        return externalProductService.streamProducts();
    }
}
