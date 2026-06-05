package com.communitymarketplace.marketplacebackend.repository;

import com.communitymarketplace.marketplacebackend.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface WishlistRepository
        extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserEmail(String userEmail);
    boolean existsByUserEmailAndProductId(
            String userEmail,
            Long productId
    );
    @Modifying
    void deleteByUserEmailAndProductId(
            String userEmail,
            Long productId
    );
}