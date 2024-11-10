package com.example.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.model.Warehouse;
import com.example.productmanagement.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // Get all warehouses
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // Get warehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        return warehouse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new warehouse
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseService.saveWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
    }

    // Update an existing warehouse
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouse) {
        Optional<Warehouse> existingWarehouse = warehouseService.getWarehouseById(id);
        if (existingWarehouse.isPresent()) {
            warehouse.setId(id);  // Set the ID of the warehouse to update
            Warehouse updatedWarehouse = warehouseService.saveWarehouse(warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete a warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
