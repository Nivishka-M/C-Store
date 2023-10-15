package com.cstore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VariantDto {
    private List<PropertyDto> properties;
    private BigDecimal weight;
}
