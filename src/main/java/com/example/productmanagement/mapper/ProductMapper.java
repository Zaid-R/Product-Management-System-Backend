package com.example.productmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO productDTO);
    
    ProductDTO toDTO(Product product);
}