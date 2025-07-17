package com.pavel.store.service;

import com.pavel.store.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> resolveOrCreateOrderItems(List<OrderItem> orderItems);
}
