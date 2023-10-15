package com.cstore.domain.product.browse;

import com.cstore.dao.product.ProductDao;
import com.cstore.dao.property.PropertyDao;
import com.cstore.dao.varieson.VariesOnDAO;
import com.cstore.domain.category.browse.ProductDTO;
import com.cstore.model.product.Product;
import com.cstore.model.product.Property;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductBrowsingService {
    private final ProductDao productDAO;
    private final PropertyDao propertyDAO;
    private final VariesOnDAO variesOnDAO;

    public ProductBrowsingService(ProductDao productDAO, PropertyDao propertyDAO, VariesOnDAO variesOnDAO) {
        this.productDAO = productDAO;
        this.propertyDAO = propertyDAO;
        this.variesOnDAO = variesOnDAO;
    }


    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setBasePrice(product.getBasePrice());
            productDTO.setBrand(product.getBrand());
            productDTO.setImageUrl(product.getImageUrl());

            Map<String, List<String>> propertyMap = new HashMap<>();
            List<Property> properties = propertyDAO.findByProductId(product.getProductId());
            for (Property property : properties) {
                if (property.getPriceIncrement().compareTo(new BigDecimal("0")) == 0) {
                    if (propertyMap.containsKey(property.getPropertyName())) {
                        propertyMap.get(property.getPropertyName()).add(property.getValue());
                    } else {
                        List<String> propertyValues = new ArrayList<>() {{
                            add(property.getValue());
                        }};

                        propertyMap.put(property.getPropertyName(), propertyValues);
                    }
                }
            }
            productDTO.setProperties(propertyMap);

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<ProductDTO> getProductByName(String productName) throws SQLException {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDAO.findByName(productName);
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setBasePrice(product.getBasePrice());
            productDTO.setBrand(product.getBrand());
            productDTO.setImageUrl(product.getImageUrl());

            Map<String, List<String>> propertyMap = new HashMap<>();
            List<Property> properties = propertyDAO.findByProductId(product.getProductId());
            for (Property property : properties) {
                if (property.getPriceIncrement().compareTo(new BigDecimal("0")) == 0) {
                    if (propertyMap.containsKey(property.getPropertyName())) {
                        propertyMap.get(property.getPropertyName()).add(property.getValue());
                    } else {
                        List<String> propertyValues = new ArrayList<>() {{
                            add(property.getValue());
                        }};

                        propertyMap.put(property.getPropertyName(), propertyValues);
                    }
                }
            }
            productDTO.setProperties(propertyMap);

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
