package com.cstore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private Instant date;
    private BigDecimal totalPayment;
    private String paymentMethod;
    private String deliveryMethod;
    private String email;
    private String streetNumber;
    private String streetName;
    private String city;
    private Integer zipCode;
    List<Integer> telephoneNumbers;
}
