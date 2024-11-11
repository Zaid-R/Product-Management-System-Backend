package com.example.productmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.productmanagement.dto.SupplierProductCountDTO;
import com.example.productmanagement.model.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
    @Query("""
                SELECT new com.example.productmanagement.dto.SupplierProductCountDTO(s.name, COUNT(p))
                FROM Supplier s
                JOIN s.products p
                GROUP BY s.id
                HAVING COUNT(p) > 1
                ORDER BY COUNT(p) DESC
            """)
    List<SupplierProductCountDTO> countProductsBySupplier();

    @Query(name = "Supplier.findSuppliersWithLessThan3Products")
    List<SupplierProductCountDTO> findSuppliersWithLessThan3Products();
}
