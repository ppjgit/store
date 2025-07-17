package com.pavel.store.dao;

import com.pavel.store.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Order entities.
 */
public interface OrderDao {

    Order save(Order order);

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findAllByCustomerEmailAndCustomerPhone(String customerEmail, String customerPhone);
}
