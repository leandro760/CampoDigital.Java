package com.app.categories.domain;

import java.util.List;

/**
 * Category Service Interface
 * Defines the business operations available for Category management.
 * This interface follows the hexagonal architecture pattern.
 */
public interface ICategoryService {
    /**
     * Retrieves all categories from the system.
     * @return List of all categories
     */
    List<Category> findAll();

    /**
     * Retrieves a specific Category by its ID.
     * @param id The category ID to search for
     * @return The found Category
     * @throws HandlerException if the Category is not found
     */
    Category findById(Long id);

    /**
     * Creates a new category in the system.
     * @param category The category to create
     * @return The created category with its ID
     * @throws HandlerException if a category with the same name already exists
     */
    Category save(Category category);

    /**
     * Updates an existing category.
     * @param category The category data to update
     * @param id The ID of the category to update
     * @return The updated category
     * @throws HandlerException if the category is not found or if the new name conflicts with an existing product
     */
    Category update(Category category, Long id);

    /**
     * Deletes a category from the system.
     * @param id The ID of the category to delete
     * @throws HandlerException if the category is not found
     */
    void deleteById(Long id);
} 