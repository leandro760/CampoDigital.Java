package com.app.orders.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository Interface
 * Extends JpaRepository to provide basic CRUD operations for Order entities.
 * Includes a custom method to check for duplicate order numbers.
 */
@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    /**
     * Checks if an order with the given order number already exists.
     * @param orderNumber the order number to check
     * @return true if an order with the given order number exists, false otherwise
     */
    boolean existsByOrderNumber(String orderNumber);
}
