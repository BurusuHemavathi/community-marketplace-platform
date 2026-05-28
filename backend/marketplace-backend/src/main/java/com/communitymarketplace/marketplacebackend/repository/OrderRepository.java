package com.communitymarketplace.marketplacebackend.repository;

import com.communitymarketplace.marketplacebackend.entity.Order;
import com.communitymarketplace.marketplacebackend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByBuyer(User buyer);
}