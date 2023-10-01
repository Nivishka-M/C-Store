package com.cstore.services;

import com.cstore.models.Order;
import com.cstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(Long customerId) {
        return null;
    }

    public Order addOrderDetails(Long customerId) {
        return null;
    }

    public Order placeOrder(Long customerId, Long orderId, Map<String, Object> orderDetails) {
        return null;
    }
}
