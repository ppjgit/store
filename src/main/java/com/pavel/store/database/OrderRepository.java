package com.pavel.store.database;

import com.pavel.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);

    boolean existsByCustomerEmailAndCustomerPhone(String customerEmail, String customerPhone);
}
