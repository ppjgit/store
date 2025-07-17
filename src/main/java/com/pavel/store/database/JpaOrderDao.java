package com.pavel.store.database;

import com.pavel.store.dao.OrderDao;
import com.pavel.store.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA implementation of the OrderDao interface.
 */
@Repository
class JpaOrderDao implements OrderDao {

    private final OrderRepository orderRepository;

    @Autowired
    public JpaOrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public boolean existsByCustomerEmailAndCustomerPhone(String customerEmail, String customerPhone) {
        return orderRepository.existsByCustomerEmailAndCustomerPhone(customerEmail, customerPhone);
    }
}
