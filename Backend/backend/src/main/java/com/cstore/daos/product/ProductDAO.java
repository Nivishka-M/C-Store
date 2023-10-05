package com.cstore.daos.product;

import com.cstore.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Optional<Product> findProduct(Product unknown) throws SQLException;

    List<Product> findAll();

    Optional<Product> findById(Long productId);

    List<Product> findByName(String productName) throws SQLException;

    void save(Product product) throws SQLException;

    List<Product> findAllByCategoryId(Long categoryId) throws SQLException;
}
