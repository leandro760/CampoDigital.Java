package com.app.categories.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Category Entity
 * Represents a category in the system with its basic attributes.
 * This class is mapped to the 'categories' table in the database.
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /**
     * Unique identifier for the category.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Detailed description of the category.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String description;

} 