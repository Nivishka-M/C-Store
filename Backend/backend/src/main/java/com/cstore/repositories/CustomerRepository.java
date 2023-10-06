package com.cstore.repositories;

import com.cstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * " +
                   "FROM cstore.customer;", nativeQuery = true)
    List<User> getAllCustomers();
}
