package com.example.productmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanagement.model.Order;
import com.example.productmanagement.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    // Create or update an order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Delete an order
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
