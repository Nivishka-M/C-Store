package com.cstore.daos.category;

import com.cstore.models.Category;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCCategoryDAO implements CategoryDAO {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    @Override
    public List<Category> getAllBaseCategories() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<Category> baseCategories = new ArrayList<Category>();
        String sql = "SELECT * " +
                     "FROM base_category;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Category category = new Category();

            category.setCategoryId(resultSet.getLong("category_id"));
            category.setCategoryName(resultSet.getString("category_name"));
            category.setCategoryDescription(resultSet.getString("category_description"));

            baseCategories.add(category);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return baseCategories;
    }

    @Override
    public List<Category> getAllSubCategories(Long categoryId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        List<Category> subCategories = new ArrayList<Category>();
        String sql = "SELECT * " +
                     "FROM cstore.category " +
                     "WHERE category_id IN (SELECT sub_category_id " +
                                           "FROM cstore.sub_category " +
                                           "WHERE category_id = ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Category category = new Category();

            category.setCategoryId(resultSet.getLong("category_id"));
            category.setCategoryName(resultSet.getString("category_name"));
            category.setCategoryDescription(resultSet.getString("category_description"));

            subCategories.add(category);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return subCategories;
    }
}
