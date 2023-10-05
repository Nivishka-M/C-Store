package com.cstore.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class NewProductDto {
    private Long productId;
    private String productName;
    private BigDecimal basePrice;
    private String brand;
    private String description;
    private byte[] mainImage;
    private List<VariantDto> variants;
    private List<CategoryDto> categories;
}
