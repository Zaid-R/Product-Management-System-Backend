package com.example.productmanagement.repository.impl;

import java.util.List;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.ProductRepoCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ProductRepoImpl implements ProductRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAllWithDynamicOrder(Boolean isAscending) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);

        // Set dynamic ordering based on the 'ascending' boolean value
        if (isAscending != null && isAscending) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("name")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("name")));
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
