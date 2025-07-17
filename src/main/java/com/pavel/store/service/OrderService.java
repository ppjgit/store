package com.pavel.store.service;

import com.pavel.store.dto.OrderDto;

import java.util.Optional;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    Optional<OrderDto> getOrderByOrderNumber(String orderNumber);
}
