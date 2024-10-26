package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDTO;
import com.example.productmanagement.mapper.ProductMapper;
import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product")
public class ProductController {

    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(description = "Retrieve a list of all products.", summary = "Get all products", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", examples = {
                @ExampleObject(value = """
                            [
                                {
                                    "id": 1,
                                    "name": "Sample Product",
                                    "price": 99.99,
                                    "description": "This is a sample product."
                                }
                            ]
                        """)
        }))
    })
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = service.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(description = "Retrieve a specific product by its ID.", summary = "Get product by ID", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = """
                                {
                                    "id": 1,
                                    "name": "Sample Product",
                                    "price": 99.99,
                                    "description": "This is a sample product."
                                }
                            """)
            })),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content(mediaType = "String", examples = {
                    @ExampleObject(value = "Product with ID 2 not found") }, schema = @Schema(type = "String")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(description = "Create a new product with the provided details.", summary = "Add a new product", responses = {
            @ApiResponse(description = "Success", responseCode = "201"),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content(mediaType = "String", examples = {
                    @ExampleObject(value = "Product with ID 2 not found") }, schema = @Schema(type = "String"))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = """
                            {
                                "name": "Product name must not be empty"
                            }
                            """) }, schema = @Schema(type = "String"))),
    })
    @PostMapping
    public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        service.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(description = "Update an existing product's details by its ID.", summary = "Update product by ID", responses = {
            @ApiResponse(description = "Success", responseCode = "204"),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content(mediaType = "String", examples = {
                    @ExampleObject(value = "Product with ID 2 not found") }, schema = @Schema(type = "String"))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = """
                            {
                                "name": "Product name must not be empty"
                            }
                            """) }, schema = @Schema(type = "String"))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable long id, @Valid @RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product.setId(id);
        service.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Delete a specific product by its ID.", summary = "Delete product by ID", responses = {
            @ApiResponse(description = "Success", responseCode = "204"),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content(mediaType = "String", examples = {
                    @ExampleObject(value = "Product with ID 2 not found") }, schema = @Schema(type = "String"))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
