package com.app.categories.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.categories.domain.Category;
import com.app.categories.domain.ICategoryService;
import com.app.categories.infrastructure.CategoryDatasource;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryDatasource categoryDatasource;

    @Override
    public List<Category> findAll() {
        return categoryDatasource.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDatasource.findById(id);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryDatasource.save(category);
    }

    @Override
    @Transactional
    public Category update(Category category, Long id) {
        return categoryDatasource.update(category, id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryDatasource.deleteById(id);
    }
} 