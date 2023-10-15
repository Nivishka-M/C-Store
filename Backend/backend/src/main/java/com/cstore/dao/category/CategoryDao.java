package com.cstore.dao.category;

import com.cstore.model.category.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    List<Category> findAll();

    Optional<Category> findCategory(Category unknown);

    Optional<Category> findById(Long categoryId);

    Long save(Category category);

    Category update(Category category);

    Category delete(Long categoryId);

    List<Category> findAllBaseCategories();

    List<Category> findAllDirectSubCategories(Long categoryId);

    List<Category> findByProductId(Long productId);
}
