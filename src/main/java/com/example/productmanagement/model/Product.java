package com.example.productmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
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

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn
    private Supplier supplier;
    
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "product",fetch = FetchType.EAGER)
    private List<InventoryRecord> inventoryRecords;

    @OneToMany
    private List<OrderItem> orderItems;

    public Product(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
    }
}
