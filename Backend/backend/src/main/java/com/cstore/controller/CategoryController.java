package com.cstore.controller;

import com.cstore.model.category.Category;
import com.cstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{category_id}")
    public Category getCategoryById(@PathVariable(name = "category_id", required = true) Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public Category addNewCategory(@RequestBody(required = true) Category category) throws SQLException {
        return categoryService.addNewCategory(category);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{category_id}")
    public Category updateCategory(@PathVariable(name = "category_id", required = true) Long categoryId,
                                   @RequestBody(required = true) Map<String, Object> newDetails) throws SQLException {
        return categoryService.updateCategory(categoryId, newDetails);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{category_id}")
    public void deleteCategory(@PathVariable(name = "category_id", required = true) Long categoryId) throws SQLException {
        categoryService.deleteCategory(categoryId);
    }
}
