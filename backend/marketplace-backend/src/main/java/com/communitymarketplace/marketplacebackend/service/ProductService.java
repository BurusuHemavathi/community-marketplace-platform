package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.ProductRequestDTO;
import com.communitymarketplace.marketplacebackend.dto.ProductResponseDTO;
import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.exception.ResourceNotFoundException;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE PRODUCT

    public ProductResponseDTO addProduct(
            ProductRequestDTO productDTO,
            String email
    ) {

        User seller = userRepository.findByEmail(email);

        Product product = new Product();

        product.setProductName(productDTO.getTitle());

        product.setDescription(productDTO.getDescription());

        product.setPrice(productDTO.getPrice());

        product.setCategory(productDTO.getCategory());

        product.setSeller(seller);

        Product savedProduct = productRepository.save(product);

        return mapToResponseDTO(savedProduct);
    }

    // GET ALL PRODUCTS

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET PRODUCT BY ID

    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        return mapToResponseDTO(product);
    }

    // UPDATE PRODUCT

    public ProductResponseDTO updateProduct(
            Long id,
            ProductRequestDTO productDTO
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        product.setProductName(productDTO.getTitle());

        product.setDescription(productDTO.getDescription());

        product.setPrice(productDTO.getPrice());

        product.setCategory(productDTO.getCategory());

        Product updatedProduct = productRepository.save(product);

        return mapToResponseDTO(updatedProduct);
    }

    // DELETE PRODUCT

    public String deleteProduct(Long id, String email) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        // CHECK OWNER

        if (!product.getSeller().getEmail().equals(email)) {

            throw new RuntimeException(
                    "You can delete only your own products"
            );
        }

        productRepository.delete(product);

        return "Product deleted successfully";
    }
    // ENTITY → DTO

    private ProductResponseDTO mapToResponseDTO(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());

        dto.setProductName(product.getProductName());

        dto.setDescription(product.getDescription());

        dto.setPrice(product.getPrice());

        dto.setCategory(product.getCategory());

        dto.setSellerName(product.getSeller().getName());

        return dto;
    }
    public List<ProductResponseDTO> searchProducts(String keyword) {

        List<Product> products =
                productRepository.findByProductNameContainingIgnoreCase(keyword);

        return products.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }
    public Page<ProductResponseDTO> getAllProducts(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        Page<Product> productPage =
                productRepository.findAll(pageable);

        return productPage.map(this::mapToResponseDTO);
    }
}