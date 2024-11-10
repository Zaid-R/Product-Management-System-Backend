package com.example.productmanagement.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacity;
    private String contactNumber;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnore
    private List<InventoryRecord> inventoryRecords;

    public Warehouse(){}

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", capacity=" + capacity + ", contactNumber=" + contactNumber + "]";
    }
}
