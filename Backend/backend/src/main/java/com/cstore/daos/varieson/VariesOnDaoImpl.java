package com.cstore.daos.varieson;

import com.cstore.daos.product.ProductDAO;
import com.cstore.daos.property.PropertyDAO;
import com.cstore.daos.variant.VariantDao;
import com.cstore.models.Product;
import com.cstore.models.VariesOn;
import com.cstore.models.VariesOnId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VariesOnDaoImpl implements VariesOnDao {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    private final ProductDAO productDAO;
    private final PropertyDAO propertyDAO;
    private final VariantDao variantDAO;

    Logger logger = LoggerFactory.getLogger(VariesOnDaoImpl.class);

    @Autowired
    public VariesOnDaoImpl(ProductDAO productDAO, PropertyDAO propertyDAO, VariantDao variantDAO) {
        this.productDAO = productDAO;
        this.propertyDAO = propertyDAO;
        this.variantDAO = variantDAO;
    }

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

                    variesOn.setId(variesOnId);
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
}
