package com.cstore.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_contact")
public class CustomerContact {
    @EmbeddedId
    private CustomerContactId id;

    @MapsId("customerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerContactId getId() {
        return id;
    }

    public void setId(CustomerContactId id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}