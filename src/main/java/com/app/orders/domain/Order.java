package com.app.orders.domain;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Order Entity
 * Represents an order in the system with its basic attributes.
 * This class is mapped to the 'orders' table in the database.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * Unique identifier for the order.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique order number.
     * Cannot be null and must be unique.
     */
    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    /**
     * Detailed description of the order.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String description;

    /**
     * Total price of the order.
     * Cannot be null.
     */
    @Column(name = "order_value", nullable = false)
    private Double orderValue;

    /**
     * Date when the order was placed.
     * Cannot be null.
     */
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    /**
     * Date when the order was shipped.
     * May be null if not yet shipped.
     */
    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    /**
     * Date when the order was delivered.
     * May be null if not yet delivered.
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    /**
     * Additional details about the order.
     */
    @Lob
    @Column(name = "order_detail")
    private String orderDetail;

    /**
     * Status of the order (e.g., 'Pending', 'Shipped', 'Delivered').
     * Cannot be null.
     */
    @Column(nullable = false)
    private String status;
}
