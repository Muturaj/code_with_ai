# Simple Ecommerce API

Java 17 Spring Boot ecommerce sample with:

- Product CRUD using Spring Web, Spring Data JPA, and H2 in-memory database
- Swagger UI using Springdoc OpenAPI
- Reactive external API call using `WebClient`

## Run

```powershell
mvn spring-boot:run
```

The app runs at:

```text
http://localhost:8080
```

## Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

Alternate Swagger UI path:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## H2 Console

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:ecommercedb
Username: sa
Password:
```

## Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/products` | Get all products from H2 |
| GET | `/api/products/{id}` | Get one product from H2 |
| POST | `/api/products` | Create product in H2 |
| PUT | `/api/products/{id}` | Update product in H2 |
| DELETE | `/api/products/{id}` | Delete product from H2 |
| GET | `/api/external/products` | Fetch external products with reactive `WebClient`, falling back to dummy data |
| GET | `/api/external/products/dummy` | Return dummy external products without calling external host |
| GET | `/api/external/products/stream` | Stream external products as NDJSON |

External product host is configured in `src/main/resources/application.properties`:

```properties
external.products.base-url=https://fakestoreapi.com
```

So the full external URL is:

```text
https://fakestoreapi.com/products
```

## Example Create Request

```json
{
  "name": "Bluetooth Speaker",
  "description": "Portable speaker with deep bass.",
  "price": 1299.00,
  "quantity": 25,
  "category": "Electronics"
}
```
