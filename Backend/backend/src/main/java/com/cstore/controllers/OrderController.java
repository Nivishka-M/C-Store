package com.cstore.controllers;

import com.cstore.models.Order;
import com.cstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cstore/api/customers/{customer_id}")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/orders")
    public List<Order> getAllOrders(@PathVariable(name = "customer_id", required = true) Long customerId) {
        /* TODO
         *  Retrieve all pending orders of the customer.
         */
        return orderService.getAllOrders(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/orders")
    public Order addOrderDetails(@PathVariable(name = "customer_id", required = true) Long customerId) {
        /* TODO
         *  Check whether the cart is empty.
         *  If so, throw an error saying that an order of no items cannot be placed.
         *
         * TODO
         *  Check whether all the required details are provided.
         *  If not, throw an error indicating that those details are required.
         *
         * TODO
         *  Create a new empty order.
         *  Ask the frontend to process the payment & call 'placeOrder'.
         */
        return orderService.addOrderDetails(customerId);
    }

    /* This method is called immediately after the success of an order. */
    @RequestMapping(method = RequestMethod.PATCH, path = "/orders/{order_id}")
    public Order placeOrder(@PathVariable(name = "customer_id", required = true) Long customerId,
                            @PathVariable(name = "order_id", required = true) Long orderId,
                            @RequestBody(required = true)Map<String, Object> orderDetails) {
        /* TODO
         *  Update the order details, copy all the cart items from the cart to the order & empty the cart.
         */
        return orderService.placeOrder(customerId, orderId, orderDetails);
    }
}
