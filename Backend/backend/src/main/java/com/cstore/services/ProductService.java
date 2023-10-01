package com.cstore.services;

import com.cstore.models.Category;
import com.cstore.models.Product;
import com.cstore.repositories.CategoryRepository;
import com.cstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllBaseCategories() {
        return categoryRepository.findAllBaseCategories();
    }

    public List<Category> getAllSubCategories(Long categoryId) {
        return null;
    }

    public List<Product> getAllProductsBelongingToCategory(Long categoryId) {
        return productRepository.findAllProductsBelongingToCategory(categoryId);
    }
}
