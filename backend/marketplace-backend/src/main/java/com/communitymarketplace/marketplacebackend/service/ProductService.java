package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.ProductRequestDTO;
import com.communitymarketplace.marketplacebackend.dto.ProductResponseDTO;
import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ADD PRODUCT

    public ProductResponseDTO addProduct(
            ProductRequestDTO productDTO,
            String sellerEmail
    ) {

        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());

        product.setSellerEmail(sellerEmail);

        Product savedProduct = productRepository.save(product);

        return convertToDTO(savedProduct);
    }

    // GET ALL PRODUCTS

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductResponseDTO> responseList =
                new ArrayList<>();

        for(Product product : products) {

            responseList.add(convertToDTO(product));
        }

        return responseList;
    }

    // GET PRODUCT BY ID

    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        return convertToDTO(product);
    }

    // UPDATE PRODUCT

    public ProductResponseDTO updateProduct(
            Long id,
            ProductRequestDTO dto
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());

        Product updatedProduct =
                productRepository.save(product);

        return convertToDTO(updatedProduct);
    }

    // DELETE PRODUCT

    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }

    // SEARCH PRODUCTS

    public List<ProductResponseDTO> searchProducts(
            String keyword
    ) {

        List<Product> products =
                productRepository
                        .findByProductNameContainingIgnoreCase(keyword);

        List<ProductResponseDTO> responseList =
                new ArrayList<>();

        for(Product product : products) {

            responseList.add(convertToDTO(product));
        }

        return responseList;
    }

    // COMMON DTO CONVERTER

    private ProductResponseDTO convertToDTO(Product product) {

        ProductResponseDTO dto =
                new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setSellerEmail(product.getSellerEmail());

        return dto;
    }
}