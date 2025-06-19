package com.app.products.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Response DTO
 * Data Transfer Object for product responses.
 * Used to transfer product data to clients, including the product ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    /**
     * Unique identifier of the product.
     */
    private Long id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Price of the product.
     */
    private Double price;
} 