package com.cstore.domain.product.browse;

import com.cstore.dao.product.ProductDao;
import com.cstore.dao.property.PropertyDao;
import com.cstore.dao.varieson.VariesOnDao;
import com.cstore.domain.category.browse.ProductDto;
import com.cstore.model.product.Product;
import com.cstore.model.product.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductBrowsingService {
    private final ProductDao productDao;
    private final PropertyDao propertyDao;
    private final VariesOnDao variesOnDao;


    public List<ProductDto> getAllProducts() throws SQLException {
        List<ProductDto> productDtos = new ArrayList<ProductDto>();

        List<Product> products = productDao.findAll();
        for (Product product : products) {
            ProductDto productDTO = new ProductDto();

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

            productDtos.add(productDTO);
        }

        return productDtos;
    }

    public List<ProductDto> getProductByName(String productName) throws SQLException {
        List<ProductDto> productDtos = new ArrayList<ProductDto>();

        List<Product> products = productDao.findByName(productName);
        for (Product product : products) {
            ProductDto productDTO = new ProductDto();

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

            productDtos.add(productDTO);
        }

        return productDtos;
    }
}
