package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts(String nameFilter) {
        if (nameFilter == null || nameFilter.isBlank()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(nameFilter);
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        if (repository.existsBySku(product.getSku())) {
            throw new IllegalArgumentException("A product with SKU '" + product.getSku() + "' already exists.");
        }
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product update) {
        Product existing = getProductById(id);
        if (!existing.getSku().equals(update.getSku()) && repository.existsBySku(update.getSku())) {
            throw new IllegalArgumentException("A product with SKU '" + update.getSku() + "' already exists.");
        }
        existing.setName(update.getName());
        existing.setDescription(update.getDescription());
        existing.setPrice(update.getPrice());
        existing.setSku(update.getSku());
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        Product existing = getProductById(id);
        repository.delete(existing);
    }
}
