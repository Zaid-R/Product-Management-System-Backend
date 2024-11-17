package com.example.productmanagement.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@NamedQuery(name = "Supplier.findSuppliersWithLessThan3Products",
//** Using Having */
// query = """
//         SELECT new com.example.productmanagement.dto.SupplierProductCountDTO(s.name,COUNT(p))
//         FROM Supplier s
//         JOIN s.products p
//         GROUP BY s.id
//         HAVING COUNT(p) < 3
//         """

//** Using inner query */
query = """
    SELECT new com.example.productmanagement.dto.SupplierProductCountDTO(s.name, COUNT(p))
    FROM Supplier s
    JOIN s.products p
    WHERE s.id IN (
        SELECT supplier.id
        FROM Supplier supplier
        JOIN supplier.products p
        GROUP BY supplier.id
        HAVING COUNT(p) < 3
    )
    GROUP BY s.id
        """
)

public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private List<Product> products;
    
    public Supplier() {
    }
    
}
