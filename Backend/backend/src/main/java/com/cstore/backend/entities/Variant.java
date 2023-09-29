package com.cstore.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "variant", schema = "cstore")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Long id;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
