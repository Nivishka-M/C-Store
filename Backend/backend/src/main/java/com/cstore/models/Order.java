package com.cstore.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "`order`", schema = "cstore")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "date")
    private Instant date;

    @Column(name = "total_payment", precision = 12, scale = 2)
    private BigDecimal totalPayment;

    @Column(name = "payment_method", length = 20)
    private String paymentMethod;

    @Column(name = "delivery_method", length = 40)
    private String deliveryMethod;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "street_number", length = 10)
    private String streetNumber;

    @Column(name = "street_name", length = 60)
    private String streetName;

    @Column(name = "city", length = 40)
    private String city;

    @Column(name = "zipcode")
    private Integer zipcode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }
}
