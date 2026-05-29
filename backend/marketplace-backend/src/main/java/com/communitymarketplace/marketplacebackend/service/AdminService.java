package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.entity.Order;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.repository.OrderRepository;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);

        return "User deleted successfully";
    }
}