package com.communitymarketplace.marketplacebackend.controller;

import com.communitymarketplace.marketplacebackend.dto.OrderResponseDTO;
import com.communitymarketplace.marketplacebackend.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // PLACE ORDER

    @PostMapping("/orders/{productId}")
    public ResponseEntity<?> placeOrder(

            @PathVariable Long productId,

            Principal principal
    ) {

        OrderResponseDTO response =
                orderService.placeOrder(
                        productId,
                        principal.getName()
                );

        return ResponseEntity.ok(response);
    }

    // GET MY ORDERS

    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(

            Principal principal
    ) {

        List<OrderResponseDTO> orders =
                orderService.getMyOrders(
                        principal.getName()
                );

        return ResponseEntity.ok(orders);
    }
}