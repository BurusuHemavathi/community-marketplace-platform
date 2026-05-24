package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // CREATE PRODUCT
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        product.setSellerEmail(email);

        return productRepository.save(product);
    }

    // GET ALL PRODUCTS
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productRepository.findById(id).orElse(null);
    }
    // DELETE PRODUCT
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        String loggedInEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (!existingProduct.getSellerEmail().equals(loggedInEmail)) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can delete only your own products");
        }

        productRepository.delete(existingProduct);

        return ResponseEntity.ok("Product deleted successfully");
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct
    ) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        String loggedInEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (!existingProduct.getSellerEmail().equals(loggedInEmail)) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can update only your own products");
        }

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        Product savedProduct = productRepository.save(existingProduct);

        return ResponseEntity.ok(savedProduct);
    }
}