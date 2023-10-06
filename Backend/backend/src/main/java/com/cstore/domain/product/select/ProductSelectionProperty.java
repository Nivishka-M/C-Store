package com.cstore.domain.product.select;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSelectionProperty {
    private String value;
    private BigDecimal priceIncrement;
}
