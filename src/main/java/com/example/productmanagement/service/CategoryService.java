package com.example.productmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.productmanagement.model.Category;
import com.example.productmanagement.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get category by ID
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    // Create or update a category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Delete a category
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}