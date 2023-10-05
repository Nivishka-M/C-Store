package com.cstore.category.browsing;

import com.cstore.dtos.PropertyDto;
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
    private String description;
    private byte[] mainImage;
    private Map<String, List<PropertyDTO>> properties;
}
