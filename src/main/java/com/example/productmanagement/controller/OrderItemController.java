package com.example.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.model.OrderItem;
import com.example.productmanagement.model.key.OrderItemKey;
import com.example.productmanagement.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // Get order item by composite key
    @GetMapping("/{productId}/{orderId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int productId, @PathVariable int orderId) {
        OrderItemKey id = new OrderItemKey(productId, orderId);
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderItem);
    }

    // Update an existing order item
    @PutMapping("/{productId}/{orderId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int productId, @PathVariable int orderId, @RequestBody OrderItem orderItem) {
        OrderItemKey id = new OrderItemKey(productId, orderId);
        Optional<OrderItem> existingOrderItem = orderItemService.getOrderItemById(id);
        if (existingOrderItem.isPresent()) {
            orderItem.setId(id);  // Set the composite ID of the order item
            OrderItem updatedOrderItem = orderItemService.saveOrderItem(orderItem);
            return ResponseEntity.ok(updatedOrderItem);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete an order item
    @DeleteMapping("/{productId}/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int productId, @PathVariable int orderId) {
        OrderItemKey id = new OrderItemKey(productId, orderId);
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
