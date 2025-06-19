package com.app.products.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.shared.application.exception.HandlerException;
import com.app.products.domain.IProductRepository;
import com.app.products.domain.Product;

/**
 * Product Datasource
 * Implements the data access layer for Product entities.
 * Handles all database operations and error handling for products.
 */
@Repository
public class ProductDatasource {

    // Error messages for different scenarios
    private static final String PRODUCT_NOT_FOUND = "Producto no encontrado con ID: ";
    private static final String ERROR_SAVING = "Error al guardar producto: ";
    private static final String ERROR_UPDATING = "Error al actualizar producto: ";
    private static final String ERROR_DELETING = "Error al eliminar producto: ";
    private static final String ERROR_FINDING = "Error al obtener productos: ";
    private static final String ERROR_FINDING_BY_ID = "Error al obtener producto por ID: ";

    private final IProductRepository productRepository;

    /**
     * Constructor for dependency injection of the product repository.
     * @param productRepository The repository to use for database operations
     */
    public ProductDatasource(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the database.
     * @return List of all products
     * @throws HandlerException if there's an error accessing the database
     */
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Retrieves a specific product by its ID.
     * @param id The product ID to search for
     * @return The found product
     * @throws HandlerException if the product is not found or there's a database error
     */
    public Product findById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new HandlerException(PRODUCT_NOT_FOUND + id, "NOT_FOUND"));
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING_BY_ID + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Creates a new product in the database.
     * @param product The product to create
     * @return The created product with its ID
     * @throws HandlerException if a product with the same name exists or there's a database error
     */
    public Product save(Product product) {
        try {
            if (productRepository.existsByName(product.getName())) {
                throw new HandlerException("Ya existe un producto con el nombre: " + product.getName(), "VALIDATION_ERROR");
            }
            return productRepository.save(product);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_SAVING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Updates an existing product in the database.
     * @param product The product data to update
     * @param id The ID of the product to update
     * @return The updated product
     * @throws HandlerException if the product is not found, name conflicts, or there's a database error
     */
    public Product update(Product product, Long id) {
        try {
            Product existingProduct = findById(id);
            
            if (!existingProduct.getName().equals(product.getName()) && 
                productRepository.existsByName(product.getName())) {
                throw new HandlerException("Ya existe un producto con el nombre: " + product.getName(), "VALIDATION_ERROR");
            }

            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            
            return productRepository.save(existingProduct);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_UPDATING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Deletes a product from the database.
     * @param id The ID of the product to delete
     * @throws HandlerException if the product is not found or there's a database error
     */
    public void deleteById(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new HandlerException(PRODUCT_NOT_FOUND + id, "NOT_FOUND");
            }
            productRepository.deleteById(id);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_DELETING + e.getMessage(), "DATABASE_ERROR");
        }
    }
} 