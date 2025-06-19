package com.app.products.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shared.adapters.BaseController;
import com.app.products.domain.IProductService;
import com.app.products.domain.Product;
import com.app.products.infrastructure.dto.ProductRequest;
import com.app.products.infrastructure.dto.ProductResponse;
import com.app.products.infrastructure.mapper.ProductMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Product Controller
 * REST controller for handling product-related HTTP requests.
 * Provides endpoints for CRUD operations on products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    /**
     * Retrieves all products.
     * @return ResponseEntity containing a list of ProductResponse objects
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return handleRequest(() -> 
            productService.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList())
        );
    }

    /**
     * Retrieves a specific product by ID.
     * @param id The ID of the product to retrieve
     * @return ResponseEntity containing the ProductResponse object
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return handleRequest(() -> 
            productMapper.toResponse(productService.findById(id))
        );
    }

    /**
     * Creates a new product.
     * @param request The ProductRequest containing the product data
     * @return ResponseEntity containing the created ProductResponse
     */
    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        return handleRequest(() -> {
            Product product = productMapper.toEntity(request);
            return productMapper.toResponse(productService.save(product));
        });
    }

    /**
     * Updates an existing product.
     * @param request The ProductRequest containing the updated product data
     * @param id The ID of the product to update
     * @return ResponseEntity containing the updated ProductResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @Valid @RequestBody ProductRequest request, 
            @PathVariable Long id) {
        return handleRequest(() -> {
            Product product = productService.findById(id);
            productMapper.updateEntityFromRequest(product, request);
            return productMapper.toResponse(productService.update(product, id));
        });
    }

    /**
     * Deletes a product.
     * @param id The ID of the product to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return handleVoidRequest(() -> productService.deleteById(id));
    }
} 