package com.cstore.daos.category;

import com.cstore.models.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    List<Category> findAll();

    Optional<Category> findCategory(Category unknown) throws SQLException;

    Optional<Category> findById(Long categoryId);

    void save(Category category) throws SQLException;

    void update(Category category) throws SQLException;

    void delete(Long categoryId) throws SQLException;

    List<Category> findAllBaseCategories();

    List<Category> findAllDirectSubCategories(Long categoryId);

    List<Category> findByProductId(Long productId);
}
