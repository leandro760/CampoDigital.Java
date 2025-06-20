package com.app.orders.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.app.orders.domain.Order;
import com.app.orders.infrastructure.dto.OrderRequest;
import com.app.orders.infrastructure.dto.OrderResponse;

/**
 * Order Mapper
 * Handles the conversion between Order entities and DTOs.
 * Provides methods for mapping between different data representations.
 */
@Component
public class OrderMapper {

    /**
     * Converts an OrderRequest DTO to an Order entity.
     * 
     * @param request The request DTO containing order data
     * @return A new Order entity with the request data
     */
    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(request.getOrderNumber());
        order.setDescription(request.getDescription());
        order.setOrderValue(request.getTotalPrice());
        return order;
    }

    /**
     * Converts an Order entity to an OrderResponse DTO.
     * 
     * @param order The order entity to convert
     * @return A new OrderResponse DTO with the entity data
     */
    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getDescription(),
                order.getOrderValue(),
                order.getOrderDate(),
                order.getStatus());
    }

    /**
     * Updates an existing Order entity with data from an OrderRequest DTO.
     * 
     * @param order   The order entity to update
     * @param request The request DTO containing the new data
     */
    public void updateEntityFromRequest(Order order, OrderRequest request) {
        order.setOrderNumber(request.getOrderNumber());
        order.setDescription(request.getDescription());
        order.setOrderValue(request.getTotalPrice());
    }
}
