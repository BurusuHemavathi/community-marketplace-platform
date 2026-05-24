package com.communitymarketplace.marketplacebackend.repository;

import com.communitymarketplace.marketplacebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}