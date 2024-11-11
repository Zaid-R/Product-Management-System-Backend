package com.example.productmanagement.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWithInventoryDTO {
    private String productName;
    private List<InventoryRecordDTO> inventoryRecords;
    public ProductWithInventoryDTO(String productName, List<InventoryRecordDTO> inventoryRecords) {
        this.productName = productName;
        this.inventoryRecords = inventoryRecords;
    }
}
