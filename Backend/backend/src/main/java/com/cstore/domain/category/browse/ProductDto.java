package com.cstore.domain.category.browse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private BigDecimal basePrice;
    private String brand;
    private String imageUrl;
    private Map<String, List<String>> properties;
}
