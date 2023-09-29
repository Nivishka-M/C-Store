package com.cstore.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product", schema = "cstore")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "brand", length = 40)
    private String brand;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
