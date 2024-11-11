package com.example.productmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierProductCountDTO {
    private String supplierName;
    private Long productCount;  

    public SupplierProductCountDTO(String supplierName, Long productCount) {
        this.supplierName = supplierName;
        this.productCount = productCount;
    }
}
