package com.example.productapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAllProducts(@RequestParam(required = false) String name) {
        StringBuilder builder = new StringBuilder();
        productService.getAllProducts(name).forEach(product -> {
            builder.append("Name: ").append(product.getName()).append("\n");
            builder.append("Description: ").append(product.getDescription() == null ? "" : product.getDescription()).append("\n");
            builder.append("Price: ").append(product.getPrice()).append("\n");
            builder.append("SKU: ").append(product.getSku()).append("\n");
            builder.append("createdAt: ").append(product.getCreatedAt()).append("\n");
            builder.append("Id: ").append(product.getId()).append("\n");
            builder.append("updatedAt: ").append(product.getUpdatedAt()).append("\n\n");
        });

        return ResponseEntity.ok(builder.toString());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
