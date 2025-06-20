package com.app.orders.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.shared.adapters.BaseController;
import com.app.orders.domain.IOrderService;
import com.app.orders.domain.Order;
import com.app.orders.infrastructure.dto.OrderRequest;
import com.app.orders.infrastructure.dto.OrderResponse;
import com.app.orders.infrastructure.mapper.OrderMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Orders Controller
 * REST controller for handling order-related HTTP requests.
 * Provides endpoints for CRUD operations on orders.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    /**
     * Retrieves all orders.
     * 
     * @return ResponseEntity containing a list of OrderResponse objects
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return handleRequest(() -> orderService.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList()));
    }

    /**
     * Retrieves a specific order by ID.
     * 
     * @param id The ID of the order to retrieve
     * @return ResponseEntity containing the OrderResponse object
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return handleRequest(() -> orderMapper.toResponse(orderService.findById(id)));
    }

    /**
     * Creates a new order.
     * 
     * @param request The OrderRequest containing the order data
     * @return ResponseEntity containing the created OrderResponse
     */
    @PostMapping
    public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderRequest request) {
        return handleRequest(() -> {
            Order order = orderMapper.toEntity(request);
            return orderMapper.toResponse(orderService.save(order));
        });
    }

    /**
     * Updates an existing order.
     * 
     * @param request The OrderRequest containing the updated order data
     * @param id      The ID of the order to update
     * @return ResponseEntity containing the updated OrderResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(
            @Valid @RequestBody OrderRequest request,
            @PathVariable Long id) {
        return handleRequest(() -> {
            Order existingOrder = orderService.findById(id);
            orderMapper.updateEntityFromRequest(existingOrder, request);
            return orderMapper.toResponse(orderService.update(existingOrder, id));
        });
    }

    /**
     * Deletes an order.
     * 
     * @param id The ID of the order to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return handleVoidRequest(() -> orderService.deleteById(id));
    }
}
