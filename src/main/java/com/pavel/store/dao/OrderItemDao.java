package com.pavel.store.dao;

import com.pavel.store.entity.OrderItem;

import java.util.List;

/**
 * Data Access Object interface for OrderItem entities.
 */
public interface OrderItemDao {

    OrderItem save(OrderItem orderItem);

    List<OrderItem> findByOrderId(Long orderId);
}