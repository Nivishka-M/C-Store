package com.cstore.daos.variant;

import com.cstore.models.Variant;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class JDBCVariantDao implements VariantDao {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    @Override
    public Optional<Variant> findById(Long variantId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Variant variant = new Variant();
        String sql = "SELECT * " +
                     "FROM variant " +
                     "WHERE variant_id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, variantId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            variant.setVariantId(resultSet.getLong("variant_id"));
            variant.setWeight(resultSet.getBigDecimal("weight"));

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.of(variant);
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return Optional.empty();
        }
    }
}
