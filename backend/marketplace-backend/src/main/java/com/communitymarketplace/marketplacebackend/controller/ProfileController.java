package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.dto.ProfileResponseDTO;
import com.communitymarketplace.marketplacebackend.dto.UpdateProfileDTO;
import com.communitymarketplace.marketplacebackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(
            Principal principal
    ) {

        ProfileResponseDTO profile =
                userService.getProfile(
                        principal.getName()
                );

        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(

            @RequestBody
            UpdateProfileDTO updateDTO,

            Principal principal
    ) {

        ProfileResponseDTO profile =
                userService.updateProfile(
                        principal.getName(),
                        updateDTO
                );

        return ResponseEntity.ok(profile);
    }
}