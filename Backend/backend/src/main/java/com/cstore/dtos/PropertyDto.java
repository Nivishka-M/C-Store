package com.cstore.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyDto {
    private Long propertyId;
    private String propertyName;
    private String value;
    private byte[] image;
    private BigDecimal priceIncrement;
}
