package com.cstore.controllers;

import com.cstore.models.Category;
import com.cstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/cstore/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<Category> getBaseCategories() throws SQLException {
        return categoryService.getAllBaseCategories();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{category_id}")
    public List<Category> getAllSubCategories(@PathVariable(name = "category_id", required = true) Long categoryId) throws Exception {
        return categoryService.getAllSubCategories(categoryId);
    }
}
