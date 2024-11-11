package com.example.productmanagement.service;

import org.springframework.stereotype.Service;

import com.example.productmanagement.dto.SupplierProductCountDTO;
import com.example.productmanagement.model.Supplier;
import com.example.productmanagement.repository.SupplierRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    // Add a new supplier
    public void addSupplier(Supplier supplier) {
        supplierRepo.save(supplier);
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    // Get supplier by ID
    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepo.findById(id);
    }

    // Update supplier
    public Supplier updateSupplier(int id, Supplier supplier) {
        if (supplierRepo.existsById(id)) {
            supplier.setId(id);
            return supplierRepo.save(supplier);
        }
        return null;  // Or handle exception as per requirement
    }

    // Delete supplier
    public void deleteSupplier(int id) {
        supplierRepo.deleteById(id);
    }

    public List<SupplierProductCountDTO> countProductsBySupplier(){
        return supplierRepo.countProductsBySupplier();
    }

    public List<SupplierProductCountDTO> findSuppliersWithLessThan3Products(){
        return supplierRepo.findSuppliersWithLessThan3Products();
    }
}
