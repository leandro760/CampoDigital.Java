package com.app.categories.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Category Repository Interface
 * Extends JpaRepository to provide basic CRUD operations for Category entities.
 * Includes custom method to check for duplicate category names.
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Checks if a category with the given name already exists.
     * @param name The category name to check
     * @return true if a category with the given name exists, false otherwise
     */
    boolean existsByName(String name);
} 