package com.cstore.dtos;

import com.cstore.domain.product.select.ProductSelectionProperty;
import com.cstore.models.Image;
import com.cstore.models.Property;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
