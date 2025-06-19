package com.app.products.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.products.domain.IProductService;
import com.app.products.domain.Product;
import com.app.products.infrastructure.ProductDatasource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductDatasource productDatasource;

    @Override
    public List<Product> findAll() {
        return productDatasource.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDatasource.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productDatasource.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) {
        return productDatasource.update(product, id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productDatasource.deleteById(id);
    }
} 