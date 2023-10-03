package com.cstore.daos.product;

import com.cstore.models.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCProductDAO implements ProductDAO {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    @Override
    public List<Product> getAllProducts() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT * " +
                     "FROM product;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Product product = new Product();

            product.setProductId(resultSet.getLong("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setBasePrice(resultSet.getBigDecimal("base_price"));
            product.setBrand(resultSet.getString("brand"));
            product.setDescription(resultSet.getString("description"));
            product.setMainImage(resultSet.getBytes("main_image"));

            products.add(product);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long productId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Product product = new Product();
        String sql = "SELECT * " +
                     "FROM product " +
                     "WHERE product_id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {product.setProductId(resultSet.getLong("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setBasePrice(resultSet.getBigDecimal("base_price"));
            product.setBrand(resultSet.getString("brand"));
            product.setDescription(resultSet.getString("description"));
            product.setMainImage(resultSet.getBytes("main_image"));

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.of(product);
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.empty();
        }
    }
}
