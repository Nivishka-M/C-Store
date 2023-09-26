package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerContactId implements Serializable {
    private static final long serialVersionUID = 4955077205956448862L;
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "telephone_number", nullable = false)
    private int telephoneNumber;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
        CustomerContactId entity = (CustomerContactId) o;
        return Objects.equals(this.telephoneNumber, entity.telephoneNumber) &&
                Objects.equals(this.customerId, entity.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneNumber, customerId);
    }
}