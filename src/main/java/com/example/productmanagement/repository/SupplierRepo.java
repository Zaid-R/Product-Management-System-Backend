package com.example.productmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productmanagement.model.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer>{
    
}
