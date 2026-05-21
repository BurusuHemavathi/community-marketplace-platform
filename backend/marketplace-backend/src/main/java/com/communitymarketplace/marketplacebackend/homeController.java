package com.communitymarketplace.marketplacebackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public String home() {
        return "Backend is running successfully!";
    }
}