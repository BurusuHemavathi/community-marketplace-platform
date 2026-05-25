package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.LoginDTO;
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

    public String login(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(
                loginDTO.getEmail()
        );

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        boolean isPasswordCorrect =
                passwordEncoder.matches(
                        loginDTO.getPassword(),
                        user.getPassword()
                );

        if(!isPasswordCorrect) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}