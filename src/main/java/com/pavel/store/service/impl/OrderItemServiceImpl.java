package com.pavel.store.service.impl;

import com.pavel.store.dao.OrderItemDao;
import com.pavel.store.entity.OrderItem;
import com.pavel.store.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDao orderItemDao;

    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public List<OrderItem> resolveOrCreateOrderItems(List<OrderItem> orderItems) {
        return List.of();
    }
}
