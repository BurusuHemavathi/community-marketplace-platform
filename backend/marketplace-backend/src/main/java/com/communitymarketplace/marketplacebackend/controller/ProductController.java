package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // CREATE PRODUCT
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {

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

    // UPDATE PRODUCT
    @PutMapping("/products/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setProductName(updatedProduct.getProductName());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setDescription(updatedProduct.getDescription());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setSellerEmail(updatedProduct.getSellerEmail());

        return productRepository.save(product);
    }

    // DELETE PRODUCT
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {

        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return "Product Not Found";
        }

        productRepository.deleteById(id);

        return "Product Deleted Successfully";
    }
}