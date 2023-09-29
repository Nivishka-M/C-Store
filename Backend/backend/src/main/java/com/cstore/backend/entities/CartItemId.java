package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemId implements Serializable {
    private static final long serialVersionUID = 529173796380709797L;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartItemId entity = (CartItemId) o;
        return Objects.equals(this.customerId, entity.customerId) &&
                Objects.equals(this.variantId, entity.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, variantId);
    }
}
