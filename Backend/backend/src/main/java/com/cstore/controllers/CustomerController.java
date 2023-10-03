package com.cstore.controllers;

import com.cstore.models.Customer;
import com.cstore.models.RegisteredCustomer;
import com.cstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/cstore/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{customer_id}")
    public Customer getCustomerById(@PathVariable(name = "customer_id", required = true) int customerId) {
        return customerService.getCustomerById((long)customerId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public Customer joinAsGuest(@RequestBody(required = true) Customer customer) {
        return customerService.joinAsGuest(customer);
    }

    /*
        This method lets register guest customers.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public Customer register(@PathVariable(name = "id", required = true) Long id,
                             @RequestBody(required = true) RegisteredCustomer registeredCustomer) {
        /* TODO
         *  Check whether the id is null or the Customer with the given id has no items in the cart.
         *  If so, just register the customer.
         *  Otherwise, register the customer, copy cart items to the registered customer cart and delete the guest customer.
         * */
        return customerService.register(id, registeredCustomer);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
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

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public String deleteCustomer(@PathVariable(name = "id", required = true) Long id) {
        /* TODO
         *  If the id doesn't relate to a registered customer, throw an error.
         *  Otherwise return a message depending on the possibility of deletion (For example can't delete if the customer has a pending order.
         */
        return customerService.deleteCustomer(id);
    }
}
