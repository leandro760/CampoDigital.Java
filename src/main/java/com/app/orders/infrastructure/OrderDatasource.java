package com.app.orders.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.shared.application.exception.HandlerException;
import com.app.orders.domain.IOrderRepository;
import com.app.orders.domain.Order;

/**
 * Order Datasource
 * Implements the data access layer for Order entities.
 * Handles all database operations and error handling for orders.
 */
@Repository
public class OrderDatasource {

    private static final String ORDER_NOT_FOUND = "Order no encontrado con ID: ";
    private static final String ERROR_SAVING = "Error al guardar order: ";
    private static final String ERROR_UPDATING = "Error al actualizar order: ";
    private static final String ERROR_DELETING = "Error al eliminar order: ";
    private static final String ERROR_FINDING = "Error al obtener orders: ";
    private static final String ERROR_FINDING_BY_ID = "Error al obtener order por ID: ";

    private final IOrderRepository orderRepository;

    /**
     * Constructor for dependency injection of the order repository.
     * 
     * @param orderRepository the repository to be used for data operations
     */
    public OrderDatasource(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves all orders from the database.
     * 
     * @return list of all orders
     * @throws HandlerException if database access fails
     */
    public List<Order> findAll() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Retrieves an order by its ID.
     * 
     * @param id the order ID to search
     * @return the found order
     * @throws HandlerException if order is not found or database error occurs
     */
    public Order findById(Long id) {
        try {
            return orderRepository.findById(id)
                    .orElseThrow(() -> new HandlerException(ORDER_NOT_FOUND + id, "NOT_FOUND"));
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_FINDING_BY_ID + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Saves a new order to the database.
     * 
     * @param order the order to save
     * @return the saved order
     * @throws HandlerException if database error occurs
     */
    public Order save(Order order) {
        try {
            // Add validation for unique orderNumber here if necessary
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new HandlerException(ERROR_SAVING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Updates an existing order.
     * 
     * @param order the order data to update
     * @param id    the ID of the order to update
     * @return the updated order
     * @throws HandlerException if order is not found or database error occurs
     */
    public Order update(Order order, Long id) {
        try {
            Order existing = findById(id);

            // Update allowed fields
            existing.setOrderDate(order.getOrderDate());
            existing.setOrderNumber(order.getOrderNumber());
            existing.setShippingDate(order.getShippingDate());
            existing.setDeliveryDate(order.getDeliveryDate());
            existing.setOrderValue(order.getOrderValue());
            existing.setOrderDetail(order.getOrderDetail());

            return orderRepository.save(existing);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_UPDATING + e.getMessage(), "DATABASE_ERROR");
        }
    }

    /**
     * Deletes an order by its ID.
     * 
     * @param id the ID of the order to delete
     * @throws HandlerException if order is not found or database error occurs
     */
    public void deleteById(Long id) {
        try {
            if (!orderRepository.existsById(id)) {
                throw new HandlerException(ORDER_NOT_FOUND + id, "NOT_FOUND");
            }
            orderRepository.deleteById(id);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(ERROR_DELETING + e.getMessage(), "DATABASE_ERROR");
        }
    }
}
