package com.cstore.services;

import com.cstore.exceptions.CustomerAlreadyExistsException;
import com.cstore.exceptions.InvalidArgumentException;
import com.cstore.models.Customer;
import com.cstore.models.RegisteredCustomer;
import com.cstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> tempCustomer = customerRepository.findById(customerId);
        Customer customer = null;

        if (tempCustomer.isPresent()) {
            customer = tempCustomer.get();
        }
        return customer;
    }

    public Customer joinAsGuest(Customer customer) {
        if (customer.getId() == null) {
            customer.setType("Guest");
            return customerRepository.save(customer);
        }

        throw new CustomerAlreadyExistsException("Customer with already exists.");
    }

    public Customer register(Long id, RegisteredCustomer registeredCustomer) {
        if (registeredCustomer.getEmail() == null) {
            throw new InvalidArgumentException("A valid email address must be provided.");
        } else if (registeredCustomer.getPassword() == null) {
            throw new InvalidArgumentException("A valid password must be provided.");
        } else if (registeredCustomer.getFirstName() == null) {
            throw new InvalidArgumentException("A valid first name must be provided.");
        } else if (registeredCustomer.getLastName() == null) {
            throw new InvalidArgumentException("A valid last name must be provided.");
        }
        return null;
    }

    public Customer loginCustomer(Long id, Map<String, Object> loginDetails) {
        return null;
    }

    public String deleteCustomer(Long id) {
        return null;
    }
}
