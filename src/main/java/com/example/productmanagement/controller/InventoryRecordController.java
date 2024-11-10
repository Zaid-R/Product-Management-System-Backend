package com.example.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.model.InventoryRecord;
import com.example.productmanagement.model.key.InventoryRecordKey;
import com.example.productmanagement.service.InventoryRecordService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-records")
public class InventoryRecordController {

    private final InventoryRecordService inventoryRecordService;

    public InventoryRecordController(InventoryRecordService inventoryRecordService) {
        this.inventoryRecordService = inventoryRecordService;
    }

    // Get all inventory records
    @GetMapping
    public List<InventoryRecord> getAllInventoryRecords() {
        return inventoryRecordService.getAllInventoryRecords();
    }

    // Get inventory record by composite key
    @GetMapping("/{productId}/{warehouseId}")
    public ResponseEntity<InventoryRecord> getInventoryRecordById(@PathVariable int productId, @PathVariable int warehouseId) {
        InventoryRecordKey id = new InventoryRecordKey(productId, warehouseId);
        Optional<InventoryRecord> inventoryRecord = inventoryRecordService.getInventoryRecordById(id);
        return inventoryRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new inventory record
    @PostMapping
    public ResponseEntity<InventoryRecord> createInventoryRecord(@RequestBody InventoryRecord inventoryRecord) {
        InventoryRecord savedInventoryRecord = inventoryRecordService.saveInventoryRecord(inventoryRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventoryRecord);
    }

    // Update an existing inventory record
    @PutMapping("/{productId}/{warehouseId}")
    public ResponseEntity<InventoryRecord> updateInventoryRecord(@PathVariable int productId, @PathVariable int warehouseId, @RequestBody InventoryRecord inventoryRecord) {
        InventoryRecordKey id = new InventoryRecordKey(productId, warehouseId);
        Optional<InventoryRecord> existingInventoryRecord = inventoryRecordService.getInventoryRecordById(id);
        if (existingInventoryRecord.isPresent()) {
            inventoryRecord.setId(id);  // Set the composite ID of the inventory record
            InventoryRecord updatedInventoryRecord = inventoryRecordService.saveInventoryRecord(inventoryRecord);
            return ResponseEntity.ok(updatedInventoryRecord);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete an inventory record
    @DeleteMapping("/{productId}/{warehouseId}")
    public ResponseEntity<Void> deleteInventoryRecord(@PathVariable int productId, @PathVariable int warehouseId) {
        InventoryRecordKey id = new InventoryRecordKey(productId, warehouseId);
        inventoryRecordService.deleteInventoryRecord(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
