package com.example.productmanagement.repository;

import java.util.List;

import com.example.productmanagement.model.Product;

public interface ProductRepoCustom {
    List<Product> findAllWithDynamicOrder(Boolean isAscending);
}
