package com.app.products.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.app.products.domain.Product;
import com.app.products.infrastructure.dto.ProductRequest;
import com.app.products.infrastructure.dto.ProductResponse;

/**
 * Product Mapper
 * Handles the conversion between Product entities and DTOs.
 * Provides methods for mapping between different data representations.
 */
@Component
public class ProductMapper {
    
    /**
     * Converts a ProductRequest DTO to a Product entity.
     * @param request The request DTO containing product data
     * @return A new Product entity with the request data
     */
    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return product;
    }

    /**
     * Converts a Product entity to a ProductResponse DTO.
     * @param product The product entity to convert
     * @return A new ProductResponse DTO with the entity data
     */
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }

    /**
     * Updates an existing Product entity with data from a ProductRequest DTO.
     * @param product The product entity to update
     * @param request The request DTO containing the new data
     */
    public void updateEntityFromRequest(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
    }
} 