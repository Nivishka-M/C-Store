package com.cstore.model.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemId implements Serializable {
    private static final long serialVersionUID = 529173796380709797L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.variantId, entity.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, variantId);
    }
}
