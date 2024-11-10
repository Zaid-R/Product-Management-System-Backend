package com.example.productmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanagement.model.InventoryRecord;
import com.example.productmanagement.model.key.InventoryRecordKey;
import com.example.productmanagement.repository.InventoryRecordRepo;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryRecordService {

    private final InventoryRecordRepo inventoryRecordRepo;

    public InventoryRecordService(InventoryRecordRepo inventoryRecordRepo) {
        this.inventoryRecordRepo = inventoryRecordRepo;
    }

    // Get all inventory records
    public List<InventoryRecord> getAllInventoryRecords() {
        return inventoryRecordRepo.findAll();
    }

    // Get inventory record by composite key
    public Optional<InventoryRecord> getInventoryRecordById(InventoryRecordKey id) {
        return inventoryRecordRepo.findById(id);
    }

    // Create or update inventory record
    public InventoryRecord saveInventoryRecord(InventoryRecord inventoryRecord) {
        return inventoryRecordRepo.save(inventoryRecord);
    }

    // Delete an inventory record by composite key
    public void deleteInventoryRecord(InventoryRecordKey id) {
        inventoryRecordRepo.deleteById(id);
    }
}
