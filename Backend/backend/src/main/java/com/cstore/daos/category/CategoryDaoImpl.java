package com.cstore.daos.category;

import com.cstore.models.Category;
import com.cstore.models.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * " +
                     "FROM category;";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Category.class)
        );
    }

    @Override
    public Optional<Category> findCategory(Category unknown) throws SQLException {
        String sql = "SELECT * " +
                     "FROM `category` " +
                     "WHERE `category_name` = ? AND `category_description` = ?;";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, unknown.getCategoryName());
        preparedStatement.setString(2, unknown.getCategoryDescription());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            unknown.setCategoryId(resultSet.getLong("category_id"));

            return Optional.of(unknown);
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        Category category = new Category();
        String sql = "SELECT * " +
                     "FROM `category` " +
                     "WHERE `category_id` = ?;";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    category.setCategoryId(resultSet.getLong("category_id"));
                    category.setCategoryName(resultSet.getString("category_name"));
                    category.setCategoryDescription(resultSet.getString("category_description"));

                    return Optional.of(category);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException sqe) {
            logger.error("Error while fetching the category from the database.", sqe);
            return Optional.empty();
        }
    }

    @Override
    public void save(Category category) throws SQLException {;
        String sql = "INSERT INTO `category`(`category_name`, `category_description`) VALUES(?, ?);";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.setString(2, category.getCategoryDescription());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void update(Category category) throws SQLException {
        String sql = "UPDATE `category` " +
                     "SET `category_name` = ?, `category_description` = ? " +
                     "WHERE `category_id` = ?;";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.setString(2, category.getCategoryDescription());
        preparedStatement.setLong(3, category.getCategoryId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long categoryId) throws SQLException {
        String sql = "DELETE FROM `category` " +
                     "WHERE `category_id` = ?;";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, categoryId);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public List<Category> findAllBaseCategories() {
        String sql = "SELECT * " +
                     "FROM base_category;";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Category.class)
        );
    }

    @Override
    public List<Category> findAllDirectSubCategories(Long categoryId) {
        String sql = "SELECT * " +
                     "FROM category " +
                     "WHERE category_id IN (SELECT sub_category_id " +
                                           "FROM sub_category " +
                                           "WHERE category_id = ?);";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, categoryId),
                new BeanPropertyRowMapper<>(Category.class)
        );
    }

    @Override
    public List<Category> findByProductId(Long productId) {
        String sql = "CALL categories_from_product(?);";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, productId),
                new BeanPropertyRowMapper<>(Category.class)
        );
    }
}
