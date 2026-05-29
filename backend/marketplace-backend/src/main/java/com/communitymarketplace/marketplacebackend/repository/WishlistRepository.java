package com.communitymarketplace.marketplacebackend.repository;

import com.communitymarketplace.marketplacebackend.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository
        extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserEmail(String userEmail);
}