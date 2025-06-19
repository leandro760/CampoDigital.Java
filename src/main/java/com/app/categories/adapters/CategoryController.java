package com.app.categories.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shared.adapters.BaseController;
import com.app.categories.domain.ICategoryService;
import com.app.categories.domain.Category;
import com.app.categories.infrastructure.dto.CategoryRequest;
import com.app.categories.infrastructure.dto.CategoryResponse;
import com.app.categories.infrastructure.mapper.CategoryMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Category Controller
 * REST controller for handling category-related HTTP requests.
 * Provides endpoints for CRUD operations on categories.
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    /**
     * Retrieves all categories.
     * 
     * @return ResponseEntity containing a list of CategoryResponse objects
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return handleRequest(() -> categoryService.findAll().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList()));
    }

    /**
     * Retrieves a specific category by ID.
     * 
     * @param id The ID of the category to retrieve
     * @return ResponseEntity containing the CategoryResponse object
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return handleRequest(() -> categoryMapper.toResponse(categoryService.findById(id)));
    }

    /**
     * Creates a new category.
     * 
     * @param request The CategoryRequest containing the category data
     * @return ResponseEntity containing the created CategoryResponse
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        return handleRequest(() -> {
            Category category = categoryMapper.toEntity(request);
            Category saved = categoryService.save(category);
            return categoryMapper.toResponse(saved);
        });
    }

    /**
     * Updates an existing category.
     * 
     * @param request The CategoryRequest containing the updated category data
     * @param id      The ID of the category to update
     * @return ResponseEntity containing the updated CategoryResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @Valid @RequestBody CategoryRequest request,
            @PathVariable Long id) {
        return handleRequest(() -> {
            Category category = categoryService.findById(id);
            categoryMapper.updateEntityFromRequest(category, request);
            Category updated = categoryService.update(category, id);
            return categoryMapper.toResponse(updated);
        });
    }

    /**
     * Deletes a category by ID.
     * 
     * @param id The ID of the category to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return handleVoidRequest(() -> categoryService.deleteById(id));
    }
}
