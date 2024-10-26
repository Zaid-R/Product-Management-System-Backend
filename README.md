# Product Management System
This is a RESTful Product Management System designed to manage products with features like listing, retrieving by ID, creating, updating, and deleting products. The system leverages Spring Boot for backend development, with a simple front end to interact with the system using JSF (JavaServer Faces) and PrimeFaces. It supports data validation, exception handling, and clean response handling using a structured API format.

## Features

* Retrieve All Products: Fetches a list of all products.
* Retrieve Product by ID: Gets details of a specific product by ID.
* Create New Product: Adds a new product with validation.
* Update Product by ID: Updates details of an existing product by ID.
* Delete Product by ID: Deletes a product by its ID.

## Technologies Used
* Java 17: Programming language for the application.
* Spring Boot 3.3.4: Framework for building the RESTful API and backend services.
* Spring Boot Starters:
    * **spring-boot-starter-web**: For creating web applications and RESTful APIs.
    * **spring-boot-starter-data-jpa**: For integrating JPA (Java Persistence API) to manage data persistence.
    * **spring-boot-starter-validation**: For validating user inputs and data.
    * **spring-boot-starter-test**: For unit and integration testing.
    * **spring-boot-devtools**: For development-time features such as automatic restarts.

* PostgreSQL: Database management system for data storage.
* MapStruct: Java library for mapping DTOs and entity classes.
* Lombok: For reducing boilerplate code with annotations like @Data and @Getter.
* SpringDoc OpenAPI: For generating OpenAPI 3 documentation for the REST API.

## Setup and Installation
## Clone the Repository:

```bash
git clone https://github.com/your-username/product-management-system.git
cd product-management-system
```

## Build the Project:

Ensure you have Maven installed, then run:

```bash
mvn clean install
```

## Run the Application:

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

## Access the API Documentation:

Swagger documentation is available at [http://localhost:5000/swagger-ui.html](http://localhost:5000/swagger-ui.html)

## Access the Frontend Interface:

The frontend for viewing and managing products can be accessed at [http://localhost:5000/products.xhtml](http://localhost:5000/products.xhtml).

## API Endpoints
The following endpoints are available in this system:

### Get All Products
- **URL**: `/api/v1/products`
- **Method**: GET
- **Response**: 200 OK, JSON array of products

### Get Product by ID
- **URL**: `/api/v1/products/{id}`
- **Method**: GET
- **Response**: 200 OK if found, 404 NOT FOUND if the product does not exist

### Create New Product
- **URL**: `/api/v1/products`
- **Method**: POST
- **Request Body**: JSON with product details (name, price, description)
- **Response**: 201 CREATED if successful, 400 BAD REQUEST if validation fails

### Update Product by ID
- **URL**: `/api/v1/products/{id}`
- **Method**: PUT
- **Request Body**: JSON with updated product details
- **Response**: 204 NO CONTENT if updated, 404 NOT FOUND if product does not exist

### Delete Product by ID
- **URL**: `/api/v1/products/{id}`
- **Method**: DELETE
- **Response**: 204 NO CONTENT if deleted, 404 NOT FOUND if product does not exist

## Exception Handling
The application handles exceptions globally using `@ControllerAdvice`:

- **ResourceNotFoundException**: Returns a 404 status with a message.
- **MethodArgumentNotValidException**: Returns a 400 status with detailed validation error messages.

## Validation
Products require validation for fields like name, price, and description:

- **name**: Non-null, between 2 and 50 characters.
- **price**: Positive value with up to two decimal places.
- **description**: Between 2 and 255 characters.

## Testing
To test the endpoints, you can use the Swagger UI documentation or tools like Postman and cURL. Additionally, a `ProductDTO` class is used to manage data transfer and validation when adding or updating products.

## Contact
For questions or issues, please reach out to the maintainer:

- **Maintainer Name**: Zaid Rajab
- **Email**: zaid.rjab1@gmail.com
- **GitHub**: [Zaid-R](https://github.com/Zaid-R)
