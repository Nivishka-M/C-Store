package com.cstore.controllers;

import com.cstore.models.Customer;
import com.cstore.models.RegisteredCustomer;
import com.cstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/cstore/api")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{id}")
    public Customer getCustomerById(@PathVariable(name = "id", required = true) Long id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers")
    public Customer addCustomer(@RequestBody(required = true) Customer customer) {
        /* TODO
         *  If the customer is a guest customer, simply add the customer to the `customer` table.
         *
         * TODO
         *  If the customer wants to register, add the customer to the `customer` table & throw an error with the
         *  `customer_id` asking the customer to add necessary information.
         */
        return customerService.addCustomer(customer);
    }

    /*
        This method lets register guest customers.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/customers/{id}")
    public Customer registerCustomer(@PathVariable(name = "id", required = true) Long id,
                                     @RequestBody(required = true) RegisteredCustomer registeredCustomer) {
        /* TODO
         *  Check whether all the required attributes of the customer are satisfied.
         *  If not, throw an error indicating that those fields are required.
         *
         * TODO
         *  Check whether the id is null or the Customer with the given id has no items in the cart.
         *  If so, just register the customer.
         *  Otherwise, register the customer, copy cart items to the registered customer cart and delete the guest customer.
         * */
        return customerService.registerCustomer(id, registeredCustomer);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/customers/{id}")
    public Customer loginCustomer(@PathVariable(name = "id", required = false) Long id,
                                  @RequestBody(required = true) Map<String, Object> loginDetails) {
        /* TODO
         *  Check whether all the login details are matched to a registered customer.
         *  If not, throw an error indicating that login failed.
         *
         * TODO
         *  Check whether the id is null.
         *  If so, just log the customer in.
         *  Otherwise, log the customer in, copy cart items (if any) to the registered customer cart and delete the guest customer.
         */
        return customerService.loginCustomer(id, loginDetails);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/customers/{id}")
    public String deleteCustomer(@PathVariable(name = "id", required = true) Long id) {
        /* TODO
         *  If the id doesn't relate to a registered customer, throw an error.
         *  Otherwise return a message depending on the possibility of deletion (For example can't delete if the customer has a pending order.
         */
        return customerService.deleteCustomer(id);
    }
}
