package com.cstore.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false)
    private Integer id;

    @Column(name = "property_name", length = 20)
    private String propertyName;

    @Column(name = "value", length = 20)
    private String value;

    @Column(name = "price_increment", precision = 10, scale = 2)
    private BigDecimal priceIncrement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}