package com.cstore.daos.property;

import com.cstore.models.Product;
import com.cstore.models.Property;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropertyDAOImpl implements PropertyDAO {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    @Override
    public Optional<Property> findById(Long propertyId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Property property = new Property();
        String sql = "SELECT * " +
                     "FROM property " +
                     "WHERE property_id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, propertyId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            property.setPropertyId(resultSet.getLong("property_id"));
            property.setPropertyName(resultSet.getString("property_name"));
            property.setValue(resultSet.getString("value"));
            property.setImage(resultSet.getBytes("image"));
            property.setPriceIncrement(resultSet.getBigDecimal("price_increment"));

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.of(property);
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.empty();
        }
    }

    @Override
    public List<Property> findAllByProductId(Long productId) throws SQLException {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT DISTINCT `property_id`, `property_name`, `value`, `image`, `price_increment` " +
                     "FROM `property` NATURAL RIGHT OUTER JOIN `varies_on` " +
                     "WHERE `product_id` = ? " +
                     "ORDER BY `property_name`;";

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, productId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Property property = new Property();

            property.setPropertyId(resultSet.getLong("property_id"));
            property.setPropertyName(resultSet.getString("property_name"));
            property.setValue(resultSet.getString("value"));
            property.setImage(resultSet.getBytes("image"));
            property.setPriceIncrement(resultSet.getBigDecimal("price_increment"));

            properties.add(property);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return properties;
    }
}
