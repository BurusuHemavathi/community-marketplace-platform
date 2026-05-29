package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.dto.LoginDTO;
import com.communitymarketplace.marketplacebackend.dto.UserDTO;
import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;
import com.communitymarketplace.marketplacebackend.security.JwtUtil;
import com.communitymarketplace.marketplacebackend.dto.ProfileResponseDTO;
import com.communitymarketplace.marketplacebackend.dto.UpdateProfileDTO;

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
    public ProfileResponseDTO getProfile(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        ProfileResponseDTO dto = new ProfileResponseDTO();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    public ProfileResponseDTO updateProfile(
            String email,
            UpdateProfileDTO updateDTO
    ) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setName(updateDTO.getName());

        if (updateDTO.getPassword() != null
                && !updateDTO.getPassword().isEmpty()) {

            user.setPassword(
                    passwordEncoder.encode(
                            updateDTO.getPassword()
                    )
            );
        }

        User updatedUser =
                userRepository.save(user);

        ProfileResponseDTO dto =
                new ProfileResponseDTO();

        dto.setName(updatedUser.getName());
        dto.setEmail(updatedUser.getEmail());
        dto.setRole(updatedUser.getRole());

        return dto;
    }
}