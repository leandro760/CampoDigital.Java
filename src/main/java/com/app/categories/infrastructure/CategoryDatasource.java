package com.app.categories.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.shared.application.exception.HandlerException;
import com.app.categories.domain.ICategoryRepository;
import com.app.categories.domain.Category;

/**
 * Category Datasource
 * Implements the data access layer for Product entities.
 * Handles all database operations and error handling for categories.
 */
@Repository
public class CategoryDatasource {

    // Error messages for different scenarios
    private static final String CATEGORY_NOT_FOUND = "Category no encontrado con ID: ";
    private static final String ERROR_SAVING = "Error al guardar category: ";
    private static final String ERROR_UPDATING = "Error al actualizar category: ";
    private static final String ERROR_DELETING = "Error al eliminar category: ";
    private static final String ERROR_FINDING = "Error al obtener categories: ";
    private static final String ERROR_FINDING_BY_ID = "Error al obtener category por ID: ";

    private final ICategoryRepository categoryRepository;

    /**
     * Constructor for dependency injection of the category repository.
     * @param categoryRepository The repository to use for database operations
     */
    public CategoryDatasource(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories from the database.
     * @return List of all categories
     * @throws HandlerException if there's an error accessing the database
     */
    public List<Category> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Retrieves a specific category by its ID.
     * @param id The category ID to search for
     * @return The found category
     * @throws HandlerException if the category is not found or there's a database error
     */
    public Category findById(Long id) {
        try {
            return categoryRepository.findById(id)
                    .orElseThrow(() -> new HandlerException(CATEGORY_NOT_FOUND + id, "NOT_FOUND"));
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING_BY_ID + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Creates a new category in the database.
     * @param category The category to create
     * @return The created category with its ID
     * @throws HandlerException if a category with the same name exists or there's a database error
     */
    public Category save(Category category) {
        try {
            if (categoryRepository.existsByName(category.getName())) {
                throw new HandlerException("Ya existe un category con el nombre: " + category.getName(), "VALIDATION_ERROR");
            }
            return categoryRepository.save(category);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_SAVING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Updates an existing category in the database.
     * @param category The category data to update
     * @param id The ID of the category to update
     * @return The updated category
     * @throws HandlerException if the category is not found, name conflicts, or there's a database error
     */
    public Category update(Category category, Long id) {
        try {
            Category existingCategory = findById(id);
            
            if (!existingCategory.getName().equals(category.getName()) && 
                categoryRepository.existsByName(category.getName())) {
                throw new HandlerException("Ya existe un category con el nombre: " + category.getName(), "VALIDATION_ERROR");
            }

            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            
            
            return categoryRepository.save(existingCategory);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_UPDATING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Deletes a category from the database.
     * @param id The ID of the category to delete
     * @throws HandlerException if the category is not found or there's a database error
     */
    public void deleteById(Long id) {
        try {
            if (!categoryRepository.existsById(id)) {
                throw new HandlerException(CATEGORY_NOT_FOUND + id, "NOT_FOUND");
            }
            categoryRepository.deleteById(id);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_DELETING + e.getMessage(), "DATABASE_ERROR");
        }
    }
} 