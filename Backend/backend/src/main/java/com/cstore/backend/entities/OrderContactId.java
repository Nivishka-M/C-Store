package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderContactId implements Serializable {
    private static final long serialVersionUID = -4461710058848201778L;
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "telephone_number", nullable = false)
    private int telephoneNumber;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
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