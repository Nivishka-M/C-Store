package com.cstore.daos.category;

import com.cstore.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    List<Category> getAllBaseCategories() throws SQLException;
    List<Category> getAllSubCategories(Long categoryId) throws SQLException;
}
