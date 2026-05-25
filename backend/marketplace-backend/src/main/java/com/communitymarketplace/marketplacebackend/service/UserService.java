package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.UserDTO;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;
import com.communitymarketplace.marketplacebackend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER USER

    public User registerUser(UserDTO userDTO) {

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user.setPassword(
                passwordEncoder.encode(userDTO.getPassword())
        );

        user.setRole(userDTO.getRole());

        return userRepository.save(user);
    }

    // LOGIN USER

    public String login(UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {

            return "User Not Found";
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        userDTO.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {

            return "Invalid Password";
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}