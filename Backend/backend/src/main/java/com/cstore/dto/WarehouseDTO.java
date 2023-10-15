package com.cstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseDTO {
    private Long warehouseId;
    private String streetNumber;
    private String streetName;
    private String city;
    private Integer zipcode;
    List<Integer> telephoneNumbers;
}
