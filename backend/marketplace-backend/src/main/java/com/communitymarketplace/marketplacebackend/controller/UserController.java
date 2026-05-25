package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.dto.UserDTO;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User registerUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.registerUser(userDTO);
    }

    @PostMapping("/authenticate")
    public String login(@Valid @RequestBody UserDTO userDTO) {

        return userService.login(userDTO);
    }
}