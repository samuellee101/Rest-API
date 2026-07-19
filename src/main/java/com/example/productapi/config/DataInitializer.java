package com.example.productapi.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedDatabase(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product("Wireless Mouse", "Ergonomic wireless mouse with adjustable DPI.", new BigDecimal("29.99"), "WM-1001"));
                repository.save(new Product("Mechanical Keyboard", "Compact mechanical keyboard with RGB backlight.", new BigDecimal("89.99"), "MK-2002"));
                repository.save(new Product("USB-C Hub", "Multi-port USB-C hub with HDMI and Ethernet.", new BigDecimal("49.99"), "UH-3003"));
            }
        };
    }
}
