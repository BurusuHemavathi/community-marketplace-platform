package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.entity.Wishlist;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;
import com.communitymarketplace.marketplacebackend.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addToWishlist(
            Long productId,
            String email
    ) {

        boolean alreadyExists =
                wishlistRepository
                        .existsByUserEmailAndProductId(
                                email,
                                productId
                        );

        if (alreadyExists) {
            return "Product already in wishlist";
        }

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                ));

        Wishlist wishlist = new Wishlist();

        wishlist.setUserEmail(email);

        wishlist.setProduct(product);

        wishlistRepository.save(wishlist);

        return "Added to wishlist";
    }

    public List<Wishlist> getWishlist(String email) {
        return wishlistRepository.findByUserEmail(email);
    }
    @Transactional
    public String removeFromWishlist(
            Long productId,
            String email
    ) {

        wishlistRepository
                .deleteByUserEmailAndProductId(
                        email,
                        productId
                );

        return "Removed from wishlist";
    }
}
