package com.pavel.store.controller;

import com.pavel.store.dto.OrderDto;
import com.pavel.store.entity.Order;
import com.pavel.store.mapper.OrderMapper;
import com.pavel.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order createdOrder = orderService.createOrder(order);
        OrderDto dto = orderMapper.toDto(createdOrder);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> getOrderByOrderNumber(@PathVariable String orderNumber) {
        return orderService.getOrderByOrderNumber(orderNumber)
                .map(orderMapper::toDto)
                .map(orderDto -> new ResponseEntity<>(orderDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
