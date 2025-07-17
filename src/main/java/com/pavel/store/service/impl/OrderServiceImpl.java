package com.pavel.store.service.impl;

import com.pavel.store.dto.OrderDto;
import com.pavel.store.entity.Order;
import com.pavel.store.mapper.OrderMapper;
import com.pavel.store.repository.OrderRepository;
import com.pavel.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
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

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Convert back to DTO and return
        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(orderMapper::toDto);
    }

    private String generateOrderNumber() {
        // Generate a unique order number based on UUID
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
