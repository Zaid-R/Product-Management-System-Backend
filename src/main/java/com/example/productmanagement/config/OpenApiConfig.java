package com.example.productmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Zaid Rajab",
            email = "zaid.rjab1@gmail.com"
        ),
        description = "OpenApi documentation for product management system",
        title = "OpenApi specificaiton",
        version = "1.0"
    )
)
public class OpenApiConfig {
    
}
