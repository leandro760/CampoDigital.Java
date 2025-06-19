package com.app.categories.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.app.categories.domain.Category;
import com.app.categories.infrastructure.dto.CategoryRequest;
import com.app.categories.infrastructure.dto.CategoryResponse;

/**
 * Category Mapper
 * Convierte entre la entidad Category y sus DTOs.
 */
@Component
public class CategoryMapper {
    
    /**
     * Transforma un CategoryRequest en una entidad Category.
     * @param request DTO con los datos de entrada
     * @return nueva entidad Category
     */
    public Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }

    /**
     * Transforma una entidad Category en un CategoryResponse.
     * @param category entidad a convertir
     * @return DTO con los datos de salida
     */
    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }

    /**
     * Actualiza una entidad Category existente con los datos de un CategoryRequest.
     * @param category entidad a actualizar
     * @param request DTO con nuevos valores
     */
    public void updateEntityFromRequest(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }
}
