package com.example.productmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productmanagement.model.Warehouse;
import com.example.productmanagement.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    // Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Get warehouse by ID
    public Optional<Warehouse> getWarehouseById(int id) {
        return warehouseRepository.findById(id);
    }

    // Create or update a warehouse
    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // Delete warehouse
    public void deleteWarehouse(int id) {
        warehouseRepository.deleteById(id);
    }
}
