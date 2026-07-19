# Product API

A Spring Boot REST API for managing products.

## What it manages
This API manages a product catalog with:
- Product name
- Description
- Price
- SKU
- Timestamps for creation and updates

## Real database support
The application is configured to use H2 by default for local development.
It can also connect to a real PostgreSQL database by setting environment variables.

### PostgreSQL configuration
Set these environment variables before running:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/productdb
export SPRING_DATASOURCE_DRIVER=org.postgresql.Driver
export SPRING_DATASOURCE_USERNAME=youruser
export SPRING_DATASOURCE_PASSWORD=yourpassword
```

If you want a different DB, configure `SPRING_DATASOURCE_URL` and driver accordingly.

## Run locally

Use the Maven wrapper to start the application:

```bash
./mvnw spring-boot:run
```

Then open the API at:

- `http://localhost:8080/api/products`
- H2 console: `http://localhost:8080/h2-console` (when using H2)

## Run tests

```bash
./mvnw test
```

## Sample endpoints

- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

## Notes
- The project seeds sample products in H2 when the database is empty.
- Use the `productdb` PostgreSQL database for a real production-like setup.
