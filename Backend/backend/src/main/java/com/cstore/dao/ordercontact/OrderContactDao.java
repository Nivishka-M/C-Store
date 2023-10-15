package com.cstore.dao.ordercontact;

import com.cstore.model.order.Order;
import com.cstore.model.order.OrderContact;

import java.util.List;

public interface OrderContactDao {
    List<OrderContact> findByOrder(Order order);
}
