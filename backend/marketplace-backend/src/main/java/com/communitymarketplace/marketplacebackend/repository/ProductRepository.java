package com.communitymarketplace.marketplacebackend.repository;

import com.communitymarketplace.marketplacebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(
            String keyword);
}