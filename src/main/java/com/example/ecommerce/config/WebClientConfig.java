package com.example.ecommerce.config;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient externalProductsWebClient(
            WebClient.Builder builder,
            @Value("${external.products.base-url}") String externalProductsBaseUrl
    ) {
        return builder
                .baseUrl(Objects.requireNonNull(externalProductsBaseUrl, "external.products.base-url must not be null"))
                .build();
    }
}
