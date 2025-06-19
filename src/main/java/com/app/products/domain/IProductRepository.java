package com.app.products.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository Interface
 * Extends JpaRepository to provide basic CRUD operations for Product entities.
 * Includes custom method to check for duplicate product names.
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    /**
     * Checks if a product with the given name already exists.
     * @param name The product name to check
     * @return true if a product with the given name exists, false otherwise
     */
    boolean existsByName(String name);
} 