package com.app.products.domain;

import java.util.List;

/**
 * Product Service Interface
 * Defines the business operations available for Product management.
 * This interface follows the hexagonal architecture pattern.
 */
public interface IProductService {
    /**
     * Retrieves all products from the system.
     * @return List of all products
     */
    List<Product> findAll();

    /**
     * Retrieves a specific product by its ID.
     * @param id The product ID to search for
     * @return The found product
     * @throws HandlerException if the product is not found
     */
    Product findById(Long id);

    /**
     * Creates a new product in the system.
     * @param product The product to create
     * @return The created product with its ID
     * @throws HandlerException if a product with the same name already exists
     */
    Product save(Product product);

    /**
     * Updates an existing product.
     * @param product The product data to update
     * @param id The ID of the product to update
     * @return The updated product
     * @throws HandlerException if the product is not found or if the new name conflicts with an existing product
     */
    Product update(Product product, Long id);

    /**
     * Deletes a product from the system.
     * @param id The ID of the product to delete
     * @throws HandlerException if the product is not found
     */
    void deleteById(Long id);
} 