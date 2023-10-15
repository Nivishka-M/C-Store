package com.cstore.services;

import com.cstore.exceptions.CustomerAlreadyExistsException;
import com.cstore.exceptions.InvalidArgumentException;
import com.cstore.model.user.Role;
import com.cstore.model.user.User;
import com.cstore.model.user.RegisteredUser;
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

    public List<User> getAllCustomers() {
        return customerRepository.findAll();
    }

    public User getCustomerById(Long customerId) {
        Optional<User> tempCustomer = customerRepository.findById(customerId);
        User user = null;

        if (tempCustomer.isPresent()) {
            user = tempCustomer.get();
        }
        return user;
    }

    public User joinAsGuest(User user) {
        if (user.getUserId() == null) {
            user.setRole(Role.GUEST_CUST);
            return customerRepository.save(user);
        }

        throw new CustomerAlreadyExistsException("User with already exists.");
    }

    public User register(Long id, RegisteredUser registeredUser) {
        if (registeredUser.getEmail() == null) {
            throw new InvalidArgumentException("A valid email address must be provided.");
        } else if (registeredUser.getPassword() == null) {
            throw new InvalidArgumentException("A valid password must be provided.");
        } else if (registeredUser.getFirstName() == null) {
            throw new InvalidArgumentException("A valid first name must be provided.");
        } else if (registeredUser.getLastName() == null) {
            throw new InvalidArgumentException("A valid last name must be provided.");
        }
        return null;
    }

    public User loginCustomer(Long id, Map<String, Object> loginDetails) {
        return null;
    }

    public String deleteCustomer(Long id) {
        return null;
    }
}
