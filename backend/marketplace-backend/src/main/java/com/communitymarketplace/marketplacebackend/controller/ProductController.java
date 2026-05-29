package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.dto.ProductRequestDTO;
import com.communitymarketplace.marketplacebackend.dto.ProductResponseDTO;
import com.communitymarketplace.marketplacebackend.service.ProductService;
import com.communitymarketplace.marketplacebackend.service.CloudinaryService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryService cloudinaryService;
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

    // GET PRODUCT BY ID

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable Long id
    ) {

        ProductResponseDTO product =
                productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    // GET ALL PRODUCTS WITH PAGINATION

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

    // UPDATE PRODUCT

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(

            @PathVariable Long id,

            @Valid @RequestBody ProductRequestDTO productDTO,

            Principal principal
    ) {

        ProductResponseDTO updatedProduct =
                productService.updateProduct(
                        id,
                        productDTO,
                        principal.getName()
                );

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

        return ResponseEntity.ok(
                productService.searchProducts(keyword)
        );
    }
    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(

            @RequestParam("file")
            MultipartFile file
    ) {

        String imageUrl =
                cloudinaryService.uploadFile(file);

        return ResponseEntity.ok(imageUrl);
    }
}