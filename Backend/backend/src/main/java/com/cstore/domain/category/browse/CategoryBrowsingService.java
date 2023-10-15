package com.cstore.domain.category.browse;

import com.cstore.dao.category.CategoryDao;
import com.cstore.dao.product.ProductDao;
import com.cstore.dao.property.PropertyDao;
import com.cstore.model.category.Category;
import com.cstore.model.product.Product;
import com.cstore.model.product.Property;
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
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final PropertyDao propertyDao;

    @Autowired
    public CategoryBrowsingService(CategoryDao categoryDao, ProductDao productDao, PropertyDao propertyDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.propertyDao = propertyDao;
    }

    public List<Category> getAllBaseCategories() {
        return categoryDao.findAllBaseCategories();
    }

    public List<Category> getAllDirectSubCategories(Long categoryId) throws SQLException {
        return categoryDao.findAllDirectSubCategories(categoryId);
    }

    public List<ProductDTO> getAllProductsBelongingToCategory(Long categoryId) throws SQLException {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDao.findAllByCategoryId(categoryId);
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setBasePrice(product.getBasePrice());
            productDTO.setBrand(product.getBrand());
            productDTO.setImageUrl(product.getImageUrl());

            Map<String, List<String>> propertyMap = new HashMap<>();
            List<Property> properties = propertyDao.findByProductId(product.getProductId());
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
