package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.entity.Wishlist;
import com.communitymarketplace.marketplacebackend.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/wishlist/{productId}")
    public ResponseEntity<?> addToWishlist(
            @PathVariable Long productId,
            Principal principal
    ) {

        String message =
                wishlistService.addToWishlist(
                        productId,
                        principal.getName()
                );

        return ResponseEntity.ok(message);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<?> getWishlist(
            Principal principal
    ) {

        List<Wishlist> wishlist =
                wishlistService.getWishlist(
                        principal.getName()
                );

        return ResponseEntity.ok(wishlist);
    }
}