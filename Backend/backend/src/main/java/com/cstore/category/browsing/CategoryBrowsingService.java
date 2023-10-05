package com.cstore.category.browsing;

import com.cstore.daos.category.CategoryDao;
import com.cstore.daos.product.ProductDAO;
import com.cstore.daos.property.PropertyDAO;
import com.cstore.models.Category;
import com.cstore.models.Product;
import com.cstore.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryBrowsingService {
    private final CategoryDao categoryDao;
    private final ProductDAO productDao;
    private final PropertyDAO propertyDAO;

    @Autowired
    public CategoryBrowsingService(CategoryDao categoryDao, ProductDAO productDao, PropertyDAO propertyDAO) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.propertyDAO = propertyDAO;
    }

    public List<Category> getAllBaseCategories() throws SQLException {
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
            productDTO.setDescription(product.getDescription());
            productDTO.setMainImage(product.getMainImage());

            Map<String, List<PropertyDTO>> propertyMap = new HashMap<String, List<PropertyDTO>>();
            List<Property> properties = propertyDAO.findAllByProductId(product.getProductId());
            for (Property property : properties) {
                PropertyDTO propertyDTO = new PropertyDTO();

                propertyDTO.setValue(property.getValue());
                propertyDTO.setPriceIncrement(property.getPriceIncrement());

                if (propertyMap.containsKey(property.getPropertyName())) {
                    propertyMap.get(property.getPropertyName()).add(propertyDTO);
                } else {
                    List<PropertyDTO> propertyDTOs = new ArrayList<>() {{
                        add(propertyDTO);
                    }};

                    propertyMap.put(property.getPropertyName(), propertyDTOs);
                }
            }
            productDTO.setProperties(propertyMap);

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
