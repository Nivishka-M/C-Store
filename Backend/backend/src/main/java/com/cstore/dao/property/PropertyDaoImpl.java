package com.cstore.dao.property;

import com.cstore.model.product.Product;
import com.cstore.model.product.Property;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository

@RequiredArgsConstructor
@Comment("This class is perfect.")
public class PropertyDaoImpl implements PropertyDao {
    private final JdbcTemplate jdbcTemplate;

    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    @Override
    public Optional<Property> findById(Long propertyId) {
        String sql = "SELECT * " +
                     "FROM \"property\" " +
                     "WHERE \"property_id\" = ?;";

        try {
            Property property = jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(Property.class),
                propertyId
            );

            return Optional.of(property);
        } catch (DataAccessException | NullPointerException e) {
            return Optional.empty();
        }
    }

    @Override

    @Comment("This method is perfect.")
    public List<Property> findByProductId(Long productId) {
        String sql = "SELECT * " +
                     "FROM \"properties_from_product\"(?);";

        return jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setLong(1, productId),
                new BeanPropertyRowMapper<>(Property.class)
        );
    }
}
