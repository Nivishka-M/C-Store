package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderContactId implements Serializable {
    private static final long serialVersionUID = 6863286061375131948L;
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "telephone_number", nullable = false)
    private Integer telephoneNumber;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderContactId entity = (OrderContactId) o;
        return Objects.equals(this.telephoneNumber, entity.telephoneNumber) &&
                Objects.equals(this.orderId, entity.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneNumber, orderId);
    }
}
