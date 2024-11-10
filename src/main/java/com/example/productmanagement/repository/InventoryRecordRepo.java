package com.example.productmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productmanagement.model.InventoryRecord;
import com.example.productmanagement.model.key.InventoryRecordKey;

@Repository
public interface InventoryRecordRepo extends JpaRepository<InventoryRecord,InventoryRecordKey>{
}
