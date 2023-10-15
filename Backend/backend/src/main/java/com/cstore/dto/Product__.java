package com.cstore.dto;

import com.cstore.model.product.Image;
import com.cstore.model.product.Property;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Product__ {
    private Long productId;
    private String productName;
    private BigDecimal basePrice;
    private String brand;
    private String description;
    private String imageUrl;

    private List<Image> otherImages;

    // TODO: Can change this to return the category tree the product belongs to.
    private List<_Category> categories;

    private List<Property> properties;

    private Integer stockCount;
}
