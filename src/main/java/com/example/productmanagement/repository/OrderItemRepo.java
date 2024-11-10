package com.example.productmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productmanagement.model.OrderItem;
import com.example.productmanagement.model.key.OrderItemKey;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, OrderItemKey> {
}

