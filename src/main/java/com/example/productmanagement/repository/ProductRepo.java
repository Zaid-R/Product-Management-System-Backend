package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, ProductRepoCustom {

    @Query("SELECT p.category.name FROM Product p WHERE p.id = :id")
    String getProductCategoryNameByProductId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"inventoryRecords", "inventoryRecords.warehouse"})
    Optional<Product> findById(Long id);
}
