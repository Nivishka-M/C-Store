package com.cstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerContactId implements Serializable {
    private static final long serialVersionUID = 4535620079918483570L;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "telephone_number", nullable = false)
    private Integer telephoneNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        CustomerContactId entity = (CustomerContactId) o;
        return Objects.equals(this.telephoneNumber, entity.telephoneNumber) &&
                Objects.equals(this.customerId, entity.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneNumber, customerId);
    }
}
