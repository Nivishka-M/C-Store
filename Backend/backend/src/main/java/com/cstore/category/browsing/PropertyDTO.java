package com.cstore.category.browsing;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyDTO {
    private String value;
    private BigDecimal priceIncrement;
}
