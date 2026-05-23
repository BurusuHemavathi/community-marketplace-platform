package com.communitymarketplace.marketplacebackend.service;

import com.communitymarketplace.marketplacebackend.entity.User;
import com.communitymarketplace.marketplacebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.communitymarketplace.marketplacebackend.exception.ResourceNotFoundException;
import com.communitymarketplace.marketplacebackend.exception.ResourceNotFoundException;
import com.communitymarketplace.marketplacebackend.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // SAVE USER
    public User saveUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // GET USER BY ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // DELETE USER
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // UPDATE USER
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }
    public UserDTO convertToDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }
    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "User not found";
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }

}