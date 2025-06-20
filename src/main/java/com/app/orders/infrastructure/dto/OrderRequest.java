package com.app.orders.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Order Request DTO
 * Data Transfer Object for order creation and update requests.
 * Includes validation constraints for each field.
 */
@Data
public class OrderRequest {
    /**
     * Unique order number.
     * Cannot be blank.
     */
    @NotBlank(message = "Order number is required")
    private String orderNumber;

    /**
     * Detailed description of the order.
     * Cannot be blank.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /**
     * Total price of the order.
     * Must be a positive number.
     */
    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;
}
