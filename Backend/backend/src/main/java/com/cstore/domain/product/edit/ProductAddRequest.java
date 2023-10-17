package com.cstore.domain.product.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRequest {
    private String productName;
    private BigDecimal basePrice;
    private String brand;
    private String description;
    private String mainImageUrl;

    private List<String> otherImageUrls;
}
