package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.ProductRepo;
import com.example.productmanagement.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

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

    private ResourceNotFoundException createNotFoundException(long prodId) {
        return new ResourceNotFoundException("Product with ID " + prodId + " not found.");
    }
}
