package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.OrderResponseDTO;
import com.communitymarketplace.marketplacebackend.entity.Order;
import com.communitymarketplace.marketplacebackend.entity.Product;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.exception.ResourceNotFoundException;
import com.communitymarketplace.marketplacebackend.repository.OrderRepository;
import com.communitymarketplace.marketplacebackend.repository.ProductRepository;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // PLACE ORDER

    public OrderResponseDTO placeOrder(
            Long productId,
            String buyerEmail
    ) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"
                        )
                );

        User buyer =
                userRepository.findByEmail(buyerEmail);

        if (buyer == null) {

            throw new RuntimeException(
                    "Buyer not found"
            );
        }

        Order order = new Order();

        order.setBuyer(buyer);

        order.setProduct(product);

        order.setOrderDate(LocalDateTime.now());

        Order savedOrder =
                orderRepository.save(order);

        return mapToDTO(savedOrder);
    }

    // GET MY ORDERS

    public List<OrderResponseDTO> getMyOrders(
            String email
    ) {

        User buyer =
                userRepository.findByEmail(email);

        if (buyer == null) {

            throw new RuntimeException(
                    "User not found"
            );
        }

        List<Order> orders =
                orderRepository.findByBuyer(buyer);

        return orders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // DTO MAPPING

    private OrderResponseDTO mapToDTO(
            Order order
    ) {

        OrderResponseDTO dto =
                new OrderResponseDTO();

        dto.setOrderId(order.getId());

        dto.setBuyerName(
                order.getBuyer().getName()
        );

        dto.setProductName(
                order.getProduct().getProductName()
        );

        dto.setPrice(
                order.getProduct().getPrice()
        );

        dto.setOrderDate(
                order.getOrderDate()
        );

        return dto;
    }
}