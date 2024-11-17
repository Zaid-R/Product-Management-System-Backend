package com.example.productmanagement.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.dto.SupplierProductCountDTO;
import com.example.productmanagement.model.Supplier;
import com.example.productmanagement.service.SupplierService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Get all suppliers
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Get supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        return supplier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // // Add a new supplier
    // @PostMapping
    // public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
    //     supplierService.addSupplier(supplier);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
    // }

    // // Update existing supplier
    // @PutMapping("/{id}")
    // public ResponseEntity<Supplier> updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
    //     Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
    //     if (updatedSupplier != null) {
    //         return ResponseEntity.ok(updatedSupplier);
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }

    // // Delete supplier
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteSupplier(@PathVariable int id) {
    //     supplierService.deleteSupplier(id);
    //     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // }

    @GetMapping("/groupByProduct")
    public ResponseEntity<List<SupplierProductCountDTO>> getCoundByProduct(){
        List<SupplierProductCountDTO> result = supplierService.countProductsBySupplier();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/groupByLessThan3Products")
    public ResponseEntity<List<SupplierProductCountDTO>> getSuppliersWithLessThan3Products(){
        List<SupplierProductCountDTO> result = supplierService.findSuppliersWithLessThan3Products();
        return ResponseEntity.ok(result);
    }

    
}
