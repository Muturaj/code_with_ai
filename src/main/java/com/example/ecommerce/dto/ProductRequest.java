package com.example.ecommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 120, message = "Product name must be 120 characters or fewer")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must be 1000 characters or fewer")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity cannot be negative")
        Integer quantity,

        @NotBlank(message = "Category is required")
        @Size(max = 80, message = "Category must be 80 characters or fewer")
        String category
) {
}
