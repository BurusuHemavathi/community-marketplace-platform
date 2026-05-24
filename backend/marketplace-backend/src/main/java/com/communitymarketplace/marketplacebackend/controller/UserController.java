package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;
import com.communitymarketplace.marketplacebackend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "Hello";
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {

        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user == null) {
            return "User Not Found";
        }

        if (!passwordEncoder.matches(
                loginUser.getPassword(),
                user.getPassword())) {

            return "Invalid Password";
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return token;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        userRepository.deleteById(id);

        return "User Deleted Successfully";
    }
}