package com.pavel.store.mapper;

import com.pavel.store.dto.OrderDto;
import com.pavel.store.dto.OrderItemDto;
import com.pavel.store.entity.Order;
import com.pavel.store.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderDto toDTO(Order entity) {
        if (entity == null) {
            return null;
        }

        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerEmail(entity.getCustomerEmail());
        dto.setCustomerPhone(entity.getCustomerPhone());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getItems() != null) {
            dto.setItems(entity.getItems().stream()
                    .map(orderItemMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }

        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerEmail(dto.getCustomerEmail());
        entity.setCustomerPhone(dto.getCustomerPhone());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setCreatedAt(dto.getCreatedAt());

        if (dto.getItems() != null) {
            List<OrderItem> items = dto.getItems().stream()
                    .map(orderItemMapper::toEntity)
                    .collect(Collectors.toList());

            entity.setItems(new ArrayList<>());
            items.forEach(entity::addItem);
        }

        return entity;
    }

    public void updateEntityFromDTO(OrderDto dto, Order entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerEmail(dto.getCustomerEmail());
        entity.setCustomerPhone(dto.getCustomerPhone());
        entity.setTotalAmount(dto.getTotalAmount());

        // Order number and created date typically shouldn't be updated
    }
}
