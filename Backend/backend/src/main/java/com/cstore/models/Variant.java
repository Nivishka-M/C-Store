package com.cstore.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "variant", schema = "cstore")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
