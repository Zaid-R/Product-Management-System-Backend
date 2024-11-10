package com.example.productmanagement.model.key;

import java.io.Serializable;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Warehouse;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class InventoryRecordKey implements Serializable{

    private int productId;
    private int warehouseId;
    
    public InventoryRecordKey(int productId, int warehouseId) {
        this.productId = productId;
        this.warehouseId = warehouseId;
    }

    public InventoryRecordKey() {}
}
