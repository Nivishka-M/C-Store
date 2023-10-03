package com.cstore.repositories;

import com.cstore.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * " +
                   "FROM cstore.customer;", nativeQuery = true)
    List<Customer> getAllCustomers();
}
