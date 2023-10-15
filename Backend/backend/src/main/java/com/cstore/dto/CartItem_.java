package com.cstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartItem_ {
    private List<Long> propertyIds;
    private Integer quantity;
}
