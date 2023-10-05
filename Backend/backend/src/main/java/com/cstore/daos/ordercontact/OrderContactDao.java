package com.cstore.daos.ordercontact;

import com.cstore.models.Order;
import com.cstore.models.OrderContact;

import java.util.List;

public interface OrderContactDao {
    List<OrderContact> findByOrder(Order order);
}
