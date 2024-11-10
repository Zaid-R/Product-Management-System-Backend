package com.example.productmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanagement.model.OrderItem;
import com.example.productmanagement.model.key.OrderItemKey;
import com.example.productmanagement.repository.OrderItemRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    // Get all order items
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    // Get order item by composite key
    public Optional<OrderItem> getOrderItemById(OrderItemKey id) {
        return orderItemRepo.findById(id);
    }

    // Create or update an order item
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    // Delete an order item by composite key
    public void deleteOrderItem(OrderItemKey id) {
        orderItemRepo.deleteById(id);
    }
}
