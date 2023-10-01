package com.cstore.controllers;

import com.cstore.models.Category;
import com.cstore.models.Product;
import com.cstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cstore/api/categories")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<Category> getAllBaseCategories() {
        return productService.getAllBaseCategories();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{category_id}")
    public List<Category> getAllSubCategories(@PathVariable(name = "category_id", required = true) Long categoryId) {
        return productService.getAllSubCategories(categoryId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{category_id}/products")
    public List<Product> getAllProductsBelongingToCategory(@PathVariable(name = "category_id", required = true) Long categoryId) {
        return productService.getAllProductsBelongingToCategory(categoryId);
    }
}
