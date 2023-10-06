package com.cstore.domain.category.browsing;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private BigDecimal basePrice;
    private String brand;
    private String imageUrl;
    private Map<String, List<String>> properties;
}
