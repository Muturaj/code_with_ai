package com.example.ecommerce.config;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(
                    "Wireless Mouse",
                    "Compact 2.4 GHz wireless mouse with ergonomic design.",
                    BigDecimal.valueOf(799.00),
                    40,
                    "Electronics"
            ));
            productRepository.save(new Product(
                    "Mechanical Keyboard",
                    "Backlit mechanical keyboard with blue switches.",
                    BigDecimal.valueOf(2499.00),
                    18,
                    "Electronics"
            ));
            productRepository.save(new Product(
                    "Cotton T-Shirt",
                    "Regular fit cotton t-shirt for everyday wear.",
                    BigDecimal.valueOf(499.00),
                    65,
                    "Fashion"
            ));
        };
    }
}
