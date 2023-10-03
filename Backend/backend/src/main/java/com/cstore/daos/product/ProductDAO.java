package com.cstore.daos.product;

import com.cstore.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    List<Product> getAllProducts() throws SQLException;

    Optional<Product> getProductById(Long productId) throws SQLException;
}
