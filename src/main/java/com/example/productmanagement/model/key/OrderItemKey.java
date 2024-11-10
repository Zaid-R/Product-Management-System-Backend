package com.example.productmanagement.model.key;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderItemKey implements Serializable{
    private int orderId;
    private int productId;
    public OrderItemKey(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
