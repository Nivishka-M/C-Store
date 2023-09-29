package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VariesOnId implements Serializable {
    private static final long serialVersionUID = 7792216859840206269L;
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
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
        VariesOnId entity = (VariesOnId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.variantId, entity.variantId) &&
                Objects.equals(this.propertyId, entity.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, variantId, propertyId);
    }
}
