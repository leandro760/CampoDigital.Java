package com.app.products.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Product Request DTO
 * Data Transfer Object for product creation and update requests.
 * Includes validation constraints for each field.
 */
@Data
public class ProductRequest {
    /**
     * Name of the product.
     * Must not be blank.
     */
    @NotBlank(message = "El nombre es requerido")
    private String name;

    /**
     * Description of the product.
     * Must not be blank.
     */
    @NotBlank(message = "La descripción es requerida")
    private String description;

    /**
     * Price of the product.
     * Must be a positive number.
     */
    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;
} 