package com.pavel.store.service.impl;

import com.pavel.store.dao.OrderDao;
import com.pavel.store.dto.OrderDto;
import com.pavel.store.entity.Order;
import com.pavel.store.entity.OrderItem;
import com.pavel.store.mapper.OrderMapper;
import com.pavel.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        // Check for duplicate order based on customerEmail and customerPhone
        if (orderDto.getCustomerEmail() != null && orderDto.getCustomerPhone() != null &&
                orderDao.existsByCustomerEmailAndCustomerPhone(orderDto.getCustomerEmail(), orderDto.getCustomerPhone())) {
            throw new IllegalArgumentException("Duplicate order: An order with the same email and phone already exists");
        }

        // Generate a unique order number if not provided
        if (orderDto.getOrderNumber() == null || orderDto.getOrderNumber().isEmpty()) {
            orderDto.setOrderNumber(generateOrderNumber());
        }

        // Set creation time if not provided
        if (orderDto.getCreatedAt() == null) {
            orderDto.setCreatedAt(LocalDateTime.now());
        }

        // Convert DTO to entity
        Order order = orderMapper.toEntity(orderDto);

        // Check for duplicate SKUs in order items
        Set<String> skus = new HashSet<>();
        for (OrderItem item : order.getItems()) {
            if (item.getSku() != null && !skus.add(item.getSku())) {
                throw new IllegalArgumentException("Duplicate SKU: " + item.getSku() + " in order items");
            }
        }

        // Save the order
        Order savedOrder = orderDao.save(order);

        // Convert back to DTO and return
        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderByOrderNumber(String orderNumber) {
        return orderDao.findByOrderNumber(orderNumber)
                .map(orderMapper::toDto);
    }

    private String generateOrderNumber() {
        // Generate a unique order number based on UUID
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
