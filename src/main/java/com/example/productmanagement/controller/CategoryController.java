package com.example.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.productmanagement.model.Category;
import com.example.productmanagement.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // // Create or update category
    // @PostMapping
    // public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    //     Category savedCategory = categoryService.saveCategory(category);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    // }

    // // Update category
    // @PutMapping("/{id}")
    // public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
    //     Optional<Category> existingCategory = categoryService.getCategoryById(id);
    //     if (existingCategory.isPresent()) {
    //         category.setId(id);
    //         Category updatedCategory = categoryService.saveCategory(category);
    //         return ResponseEntity.ok(updatedCategory);
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }

    // // Delete category
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
    //     categoryService.deleteCategory(id);
    //     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // }
}
