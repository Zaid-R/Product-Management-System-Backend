package com.example.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.model.Order;
import com.example.productmanagement.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // Update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if (existingOrder.isPresent()) {
            order.setId(id);  // Set the ID of the order to update
            Order updatedOrder = orderService.saveOrder(order);
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
