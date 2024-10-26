package com.example.productmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product name must not be null")
    @NotBlank(message = "Product name must not be empty")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters long.")
    private String name;

    @NotNull(message = "Product price must not be null")
    @Positive(message = "Product price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Product price must have at most two decimal digits")
    private BigDecimal price;

    @Size(min = 2, max = 255, message = "Product description must be between 2 and 255 characters long.")
    @NotBlank(message = "Product description must not be empty")
    private String description;

    public Product(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product() {
    }
}
