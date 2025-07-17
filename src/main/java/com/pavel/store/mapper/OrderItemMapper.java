package com.pavel.store.mapper;

import com.pavel.store.dto.OrderItemDto;
import com.pavel.store.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemDto toDTO(OrderItem entity) {
        if (entity == null) {
            return null;
        }

        OrderItemDto dto = new OrderItemDto();
        dto.setItemName(entity.getItemName());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setSubtotal(entity.getSubtotal());
        dto.setSku(entity.getSku());

        return dto;
    }

    public OrderItem toEntity(OrderItemDto dto) {
        if (dto == null) {
            return null;
        }

        OrderItem entity = new OrderItem();
        entity.setItemName(dto.getItemName());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setSubtotal(dto.getSubtotal());
        entity.setSku(dto.getSku());

        return entity;
    }

    public void updateEntityFromDTO(OrderItemDto dto, OrderItem entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setItemName(dto.getItemName());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setSku(dto.getSku());
        // Subtotal will be calculated by the entity's @PrePersist/@PreUpdate method
    }
}
