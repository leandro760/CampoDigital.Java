package com.app.orders.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.orders.domain.IOrderService;
import com.app.orders.domain.Order;
import com.app.orders.infrastructure.OrderDatasource;

import lombok.RequiredArgsConstructor;

/**
 * Order Service
 * Implements business logic for orders.
 * Delegates to OrderDatasource for data persistence.
 */
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    
    private final OrderDatasource orderDatasource;

    @Override
    public List<Order> findAll() {
        return orderDatasource.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderDatasource.findById(id);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderDatasource.save(order);
    }

    @Override
    @Transactional
    public Order update(Order order, Long id) {
        return orderDatasource.update(order, id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderDatasource.deleteById(id);
    }
}
