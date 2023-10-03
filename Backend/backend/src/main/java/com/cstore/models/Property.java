package com.cstore.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "property", schema = "cstore")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "property_name", length = 40)
    private String propertyName;

    @Column(name = "value", length = 40)
    private String value;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "price_increment", precision = 10, scale = 2)
    private BigDecimal priceIncrement;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public BigDecimal getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(BigDecimal priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
