package com.pavel.store.service.impl;

import com.pavel.store.dao.OrderDao;
import com.pavel.store.entity.Order;
import com.pavel.store.entity.OrderItem;
import com.pavel.store.service.OrderItemService;
import com.pavel.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderItemService orderItemService) {
        this.orderDao = orderDao;
        this.orderItemService = orderItemService;
    }

    @Override
    @Transactional
    public Order createOrder(Order newOrder) {
        List<Order> duplicates = findDuplicates(newOrder);
        if (duplicates.isEmpty()) {
            // Generate a unique order number if not provided
            if (newOrder.getOrderNumber() == null || newOrder.getOrderNumber().isEmpty()) {
                newOrder.setOrderNumber(generateOrderNumber());
            }

            // Set creation time if not provided
            if (newOrder.getCreatedAt() == null) {
                newOrder.setCreatedAt(LocalDateTime.now());
            }

            if (newOrder.getItems() != null) {
                List<OrderItem> orderItems = new ArrayList<>(newOrder.getItems());
                orderItems = orderItemService.resolveOrCreateOrderItems(orderItems);
                newOrder.setItems(orderItems);
            }

            // Save the order
            return orderDao.save(newOrder);
        } else {
            return updateOrder(duplicates.getFirst(), newOrder);
        }
    }

    private List<Order> findDuplicates(Order order) {
        return orderDao.findAllByCustomerEmailAndCustomerPhone(order.getCustomerEmail(), order.getCustomerPhone());
    }

    private Order updateOrder(Order existingOrder, Order newOrder) {
        //TODO update
        return existingOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderByOrderNumber(String orderNumber) {
        return orderDao.findByOrderNumber(orderNumber);
    }

    private String generateOrderNumber() {
        // Generate a unique order number based on UUID
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
