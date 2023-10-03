package com.cstore.dtos;

import lombok.Data;

@Data
public class WarehouseVariantDTO {
    private Long variantId;
    private String sku;
    private Integer count;
}
