package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.dto.ProductRequestDTO;
import com.communitymarketplace.marketplacebackend.dto.ProductResponseDTO;
import com.communitymarketplace.marketplacebackend.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import java.security.Principal;
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE PRODUCT

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(
            @Valid @RequestBody ProductRequestDTO productDTO,
            Principal principal
    ) {

        ProductResponseDTO responseDTO =
                productService.addProduct(
                        productDTO,
                        principal.getName()
                );

        return ResponseEntity.ok(responseDTO);
    }

    // GET ALL PRODUCTS

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy

    ) {

        Page<ProductResponseDTO> products =
                productService.getAllProducts(
                        page,
                        size,
                        sortBy
                );

        return ResponseEntity.ok(products);
    }

    // GET PRODUCT BY ID

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable Long id
    ) {

        ProductResponseDTO product =
                productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    // UPDATE PRODUCT

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto
    ) {

        ProductResponseDTO updatedProduct =
                productService.updateProduct(id, dto);

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE PRODUCT

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable Long id,
            Principal principal
    ) {

        String message =
                productService.deleteProduct(
                        id,
                        principal.getName()
                );

        return ResponseEntity.ok(message);
    }

    // SEARCH PRODUCTS

    @GetMapping("/products/search")
    public ResponseEntity<?> searchProducts(
            @RequestParam String keyword
    ) {

        List<ProductResponseDTO> products =
                productService.searchProducts(keyword);

        return ResponseEntity.ok(products);
    }
}