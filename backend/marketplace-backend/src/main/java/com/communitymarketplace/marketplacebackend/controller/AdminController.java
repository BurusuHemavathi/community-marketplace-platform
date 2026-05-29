package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.entity.Order;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers() {

        List<User> users =
                adminService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<?> getAllOrders() {

        List<Order> orders =
                adminService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ) {

        String message =
                adminService.deleteUser(id);

        return ResponseEntity.ok(message);
    }
}