package com.cstore.services;

import com.cstore.daos.category.CategoryDAO;
import com.cstore.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllBaseCategories() throws SQLException {
        return categoryDAO.getAllBaseCategories();

    }

    public List<Category> getAllSubCategories(Long categoryId) throws Exception {
        return categoryDAO.getAllSubCategories(categoryId);
    }

    /*private List<ProductDTO> convert(Category category) {
        List<BelongsTo> belongings = belongsToRepository.findByCategory(category);
        List<ProductDTO> products = new ArrayList<>();

        for (BelongsTo belonging : belongings) {
            ProductDTO productDTO = new ProductDTO();
            List<PropertyDTO> properties = new ArrayList<>();

            Product product = belonging.getProduct();
            List<VariesOn> variances = variesOnRepository.findByProduct(product);

            for (VariesOn variance : variances) {
                Property property = variance.getProperty();
                PropertyDTO propertyDTO = new PropertyDTO();

                propertyDTO.setPropertyName(property.getPropertyName());
                propertyDTO.setValue(property.getValue());
                propertyDTO.setImage(property.getImage());
                propertyDTO.setPriceIncrement(property.getPriceIncrement());

                properties.add(propertyDTO);
            }

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setBasePrice(product.getBasePrice());
            productDTO.setBrand(product.getBrand());
            productDTO.setDescription(product.getDescription());
            productDTO.setMainImage(product.getMainImage());
            productDTO.setProperties(properties);
        }

        return products;
    }*/

    /*public List<ProductDTO> getAllProducts(Long categoryId) {
        return productRepository.findAllProducts();
    }*/
}
