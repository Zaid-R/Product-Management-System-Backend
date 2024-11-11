package com.example.productmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRecordDTO {
    private int stockQuantity;
    private String warehousePhoneNumber;

    public InventoryRecordDTO(int stockQuantity, String warehouseName) {
        this.stockQuantity = stockQuantity;
        this.warehousePhoneNumber = warehouseName;
    }
}
