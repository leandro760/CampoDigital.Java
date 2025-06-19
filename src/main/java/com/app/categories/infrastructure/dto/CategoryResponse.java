package com.app.categories.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category Response DTO
 * Data Transfer Object for category responses.
 * Used to transfer category data to clients, including the category ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    /**
     * Unique identifier of the category.
     */
    private Long id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Description of the category.
     */
    private String description;

} 