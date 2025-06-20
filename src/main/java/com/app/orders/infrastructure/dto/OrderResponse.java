package com.app.orders.infrastructure.dto;

import java.time.LocalDate; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order Response DTO
 * Data Transfer Object for order responses.
 * Used to transfer order data to clients, including the order ID and other relevant information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    /**
     * Unique identifier of the order.
     */
    private Long id;

    /**
     * Unique order number.
     */
    private String orderNumber;

    /**
     * Detailed description of the order.
     */
    private String description;

    /**
     * Total price of the order.
     */
    private Double totalPrice;

    /**
     * Date when the order was placed.
     */
    private LocalDate orderDate;

    /**
     * Status of the order (e.g., 'Pending', 'Shipped', 'Delivered').
     */
    private String status;
}
