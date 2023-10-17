package com.cstore.dao.varieson;

import com.cstore.dao.property.PropertyDao;
import com.cstore.dao.variant.VariantDao;
import com.cstore.model.product.Product;
import com.cstore.model.product.VariesOn;
import com.cstore.model.product.VariesOnId;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VariesOnDaoImpl implements VariesOnDao {
    private final JdbcTemplate jdbcTemplate;

    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    private final PropertyDao propertyDAO;
    private final VariantDao variantDAO;

    Logger logger = LoggerFactory.getLogger(VariesOnDaoImpl.class);

    @Override
    public List<VariesOn> findByProduct(Product product) {
        List<VariesOn> variesOnList = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM varies_on " +
                     "WHERE product_id = ?;";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, product.getProductId());

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    VariesOn variesOn = new VariesOn();
                    VariesOnId variesOnId = new VariesOnId();

                    variesOnId.setProductId(resultSet.getLong("product_id"));
                    variesOnId.setPropertyId(resultSet.getLong("property_id"));
                    variesOnId.setVariantId(resultSet.getLong("variant_id"));

                    variesOn.setVariesOnId(variesOnId);
                    variesOn.setProduct(product);
                    variesOn.setProperty(propertyDAO.findById(resultSet.getLong("property_id")).get());
                    variesOn.setVariant(variantDAO.findById(resultSet.getLong("variant_id")).get());

                    variesOnList.add(variesOn);
                }

                return variesOnList;
            }
        } catch (SQLException sqe) {
            logger.error("Error while fetching variances from the database.", sqe);
            return new ArrayList<>();
        }
    }

    @Override
    public VariesOnId save(VariesOnId variesOnId) {
        String sql = "INSERT INTO \"varies_on\"(\"product_id\", \"property_id\", \"variant_id\") " +
                     "VALUES(?, ?, ?);";

        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setLong(1, variesOnId.getProductId());
                ps.setLong(2, variesOnId.getPropertyId());
                ps.setLong(3, variesOnId.getVariantId());

                return ps;
            }
        );

        return variesOnId;
    }
}
