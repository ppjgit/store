package com.pavel.store.dao;

import com.pavel.store.entity.Order;

import java.util.Optional;

/**
 * Data Access Object interface for Order entities.
 */
public interface OrderDao {
    /**
     * Save an order to the database.
     *
     * @param order the order to save
     * @return the saved order
     */
    Order save(Order order);

    /**
     * Find an order by its order number.
     *
     * @param orderNumber the order number to search for
     * @return an Optional containing the order if found, or empty if not found
     */
    Optional<Order> findByOrderNumber(String orderNumber);

    /**
     * Check if an order with the given customer email and phone already exists.
     *
     * @param customerEmail the customer email to check
     * @param customerPhone the customer phone to check
     * @return true if an order with the given email and phone exists, false otherwise
     */
    boolean existsByCustomerEmailAndCustomerPhone(String customerEmail, String customerPhone);
}
