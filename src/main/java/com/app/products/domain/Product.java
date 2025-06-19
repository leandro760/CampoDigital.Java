package com.app.products.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Product Entity
 * Represents a product in the system with its basic attributes.
 * This class is mapped to the 'products' table in the database.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * Unique identifier for the product.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Detailed description of the product.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String description;

    /**
     * Price of the product.
     * Cannot be null.
     */
    @Column(nullable = false)
    private Double price;
} 