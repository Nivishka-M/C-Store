package com.cstore.services;

import com.cstore.models.Customer;
import com.cstore.models.RegisteredCustomer;
import com.cstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return null;
    }

    public Customer getCustomerById(Long id) {
        return null;
    }

    public Customer addCustomer(Customer customer) {
        return null;
    }

    public Customer registerCustomer(Long id, RegisteredCustomer registeredCustomer) {
        return null;
    }

    public Customer loginCustomer(Long id, Map<String, Object> loginDetails) {
        return null;
    }

    public String deleteCustomer(Long id) {
        return null;
    }
}
