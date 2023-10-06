package com.cstore.domain.category.browsing;

import com.cstore.daos.category.CategoryDao;
import com.cstore.daos.product.ProductDao;
import com.cstore.daos.property.PropertyDao;
import com.cstore.models.Category;
import com.cstore.models.Product;
import com.cstore.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryBrowsingService {
    private final CategoryDao categoryDAO;
    private final ProductDao productDAO;
    private final PropertyDao propertyDAO;

    @Autowired
    public CategoryBrowsingService(CategoryDao categoryDAO, ProductDao productDAO, PropertyDao propertyDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
        this.propertyDAO = propertyDAO;
    }

    public List<Category> getAllBaseCategories() throws SQLException {
        return categoryDAO.findAllBaseCategories();

    }

    public List<Category> getAllDirectSubCategories(Long categoryId) throws SQLException {
        return categoryDAO.findAllDirectSubCategories(categoryId);
    }

    public List<ProductDTO> getAllProductsBelongingToCategory(Long categoryId) throws SQLException {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDAO.findAllByCategoryId(categoryId);
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
