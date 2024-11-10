package com.example.productmanagement.model;

import java.math.BigDecimal;

import com.example.productmanagement.model.key.OrderItemKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
@Entity
public class OrderItem
 {
    @EmbeddedId
    private OrderItemKey id;
    private int productQuantity;
    @Digits(integer = 10, fraction = 2)
    private BigDecimal priceAtPurchase;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", productQuantity=" + productQuantity + ", priceAtPurchase=" + priceAtPurchase
                + "]";
    }
}
