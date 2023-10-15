package com.cstore.dao.varieson;

import com.cstore.dao.product.ProductDao;
import com.cstore.dao.property.PropertyDao;
import com.cstore.dao.variant.VariantDao;
import com.cstore.model.product.Product;
import com.cstore.model.product.VariesOn;
import com.cstore.model.product.VariesOnId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VariesOnDAOImpl implements VariesOnDAO {
    String url = "jdbc:mysql://localhost:3306/cstore";
    String username = "cadmin";
    String password = "cstore_GRP28_CSE21";

    private final ProductDao productDAO;
    private final PropertyDao propertyDAO;
    private final VariantDao variantDAO;

    Logger logger = LoggerFactory.getLogger(VariesOnDAOImpl.class);

    @Autowired
    public VariesOnDAOImpl(ProductDao productDAO, PropertyDao propertyDAO, VariantDao variantDAO) {
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
