package com.cstore.dao.product;

import com.cstore.model.product.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> findProduct(Product unknown) throws SQLException;

    List<Product> findAll();

    Optional<Product> findById(Long productId);

    List<Product> findByName(String productName) throws SQLException;

    void save(Product product) throws SQLException;

    List<Product> findAllByCategoryId(Long categoryId) throws SQLException;

    Integer countStocks(Long productId);
}
