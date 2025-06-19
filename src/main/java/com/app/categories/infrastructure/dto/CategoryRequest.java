package com.app.categories.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Category Request DTO
 * Data Transfer Object for category creation and update requests.
 * Includes validation constraints for each field.
 */
@Data
public class CategoryRequest {
    /**
     * Name of the category.
     * Must not be blank.
     */
    @NotBlank(message = "El nombre es requerido")
    private String name;

    /**
     * Description of the category.
     * Must not be blank.
     */
    @NotBlank(message = "La descripción es requerida")
    private String description;

    
} 