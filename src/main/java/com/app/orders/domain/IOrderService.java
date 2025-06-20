package com.app.orders.domain;

import java.util.List;

/**
 * Order Service Interface
 * Defines the business operations available for Order management.
 * Follows the hexagonal architecture pattern (Ports & Adapters).
 */
public interface IOrderService {
    /**
     * Retrieves all orders from the system.
     * 
     * @return List of all orders
     */
    List<Order> findAll();

    /**
     * Retrieves a specific order by its ID.
     * 
     * @param id The order ID to search for
     * @return The found order
     * @throws HandlerException if the order is not found
     */
    Order findById(Long id);

    /**
     * Creates a new order in the system.
     * 
     * @param order The order to create
     * @return The created order with its ID
     * @throws HandlerException if an order with the same order number already
     *                          exists
     */
    Order save(Order order);

    /**
     * Updates an existing order.
     * 
     * @param order The order data to update
     * @param id    The ID of the order to update
     * @return The updated order
     * @throws HandlerException if the order is not found or if the updated order
     *                          number conflicts with an existing order
     */
    Order update(Order order, Long id);

    /**
     * Deletes an order from the system.
     * 
     * @param id The ID of the order to delete
     * @throws HandlerException if the order is not found
     */
    void deleteById(Long id);
}
