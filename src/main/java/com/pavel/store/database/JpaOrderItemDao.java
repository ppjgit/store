package com.pavel.store.database;

import com.pavel.store.dao.OrderItemDao;
import com.pavel.store.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA implementation of the OrderItemDao interface.
 */
@Repository
class JpaOrderItemDao implements OrderItemDao {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public JpaOrderItemDao(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}