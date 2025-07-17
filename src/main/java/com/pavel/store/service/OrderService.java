package com.pavel.store.service;

import com.pavel.store.entity.Order;

import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);

    Optional<Order> getOrderByOrderNumber(String orderNumber);
}
