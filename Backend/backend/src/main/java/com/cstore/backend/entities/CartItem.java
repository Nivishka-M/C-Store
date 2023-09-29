package com.cstore.backend.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_item", schema = "cstore", indexes = {@Index(name = "variant_id", columnList = "variant_id")})
public class CartItem {
    @EmbeddedId
    private CartItemId id;

    @MapsId("customerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id", nullable = false)
    private Cart customer;

    @MapsId("variantId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public Cart getCustomer() {
        return customer;
    }

    public void setCustomer(Cart customer) {
        this.customer = customer;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
