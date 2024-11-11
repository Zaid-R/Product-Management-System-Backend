package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.ProductRepo;

import jakarta.persistence.EntityNotFoundException;

import com.example.productmanagement.dto.InventoryRecordDTO;
import com.example.productmanagement.dto.ProductWithInventoryDTO;
import com.example.productmanagement.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductService {

    final ProductRepo repo;

    ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(long prodId) {
        return repo.findById(prodId)
                .orElseThrow(() -> createNotFoundException(prodId));
    }

    public void addProduct(Product prod) {
        repo.save(prod);
    }

    public void updateProduct(Product prod) {
        if (!repo.existsById(prod.getId())) {
            throw createNotFoundException(prod.getId());
        }
        repo.save(prod);
    }

    public void deleteProduct(long prodId) {
        if (!repo.existsById(prodId)) {
            throw createNotFoundException(prodId);
        }
        repo.deleteById(prodId);
    }

    public String getProductCategory(Long id) {
        return repo.getProductCategoryNameByProductId(id);
    }

    private ResourceNotFoundException createNotFoundException(long prodId) {
        return new ResourceNotFoundException("Product with ID " + prodId + " not found.");
    }

    public ProductWithInventoryDTO getProductWithInventoryRecords(long id) {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        List<InventoryRecordDTO> inventoryRecords = product.getInventoryRecords().stream()
                .map(ir -> new InventoryRecordDTO(ir.getStockQuantity(), ir.getWarehouse().getContactNumber()))
                .collect(Collectors.toList());
        return new ProductWithInventoryDTO(product.getName(), inventoryRecords);
    }

    public List<Product> getProductsOrdered(Boolean isAscending) {
        return repo.findAllWithDynamicOrder(isAscending);
    }
}
