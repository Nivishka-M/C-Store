package com.cstore.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_contact")
public class OrderContact {
    @EmbeddedId
    private OrderContactId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderContactId getId() {
        return id;
    }

    public void setId(OrderContactId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}