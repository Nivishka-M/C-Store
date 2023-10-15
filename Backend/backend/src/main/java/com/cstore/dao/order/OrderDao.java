package com.cstore.dao.order;

import com.cstore.model.order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> findAll();

    Optional<Order> findById(Long orderId);

    void deleteAll() throws SQLException;

    void deleteByID(Long orderId) throws SQLException;
}
