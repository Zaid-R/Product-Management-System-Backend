package com.example.productmanagement.model;

import com.example.productmanagement.model.key.InventoryRecordKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class InventoryRecord {

    @EmbeddedId
    private InventoryRecordKey id;
    private int stockQuantity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;

    public InventoryRecord() {
    }

    @Override
    public String toString() {
        return "InventoryRecord [id=" + id + ", stockQuantity=" + stockQuantity + "]";
    }
}
