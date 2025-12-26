package com.example.demo.service.impl;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Override
    public UserProfile createUser(UserProfile user) {
        // Check for null values
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        
        // Validate username length
        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            throw new RuntimeException("Username must be 3-50 characters");
        }
        
        // Validate email format
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }
        
        // Check for duplicate username
        if (userProfileRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check for duplicate email
        if (userProfileRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setActive(true);
        return userProfileRepository.save(user);
    }
    
    @Override
    public UserProfile getUserById(Long id) {
        Optional<UserProfile> user = userProfileRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("UserProfile not found with id: " + id);
    }
    
    @Override
    public void deactivateUser(Long id) {
        UserProfile user = getUserById(id);
        user.setActive(false);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userProfileRepository.save(user);
    }
    
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }
    
    public UserProfile updateUser(Long id, UserProfile userDetails) {
        UserProfile user = getUserById(id);
        
        if (userDetails.getUsername() != null) {
            if (userDetails.getUsername().trim().isEmpty()) {
                throw new RuntimeException("Username cannot be empty");
            }
            if (userDetails.getUsername().length() < 3 || userDetails.getUsername().length() > 50) {
                throw new RuntimeException("Username must be 3-50 characters");
            }
            
            // Check duplicate username (excluding current user)
            Optional<UserProfile> existingUser = userProfileRepository.findByUsername(userDetails.getUsername());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                throw new RuntimeException("Username already exists");
            }
            user.setUsername(userDetails.getUsername());
        }
        
        if (userDetails.getEmail() != null) {
            if (userDetails.getEmail().trim().isEmpty()) {
                throw new RuntimeException("Email cannot be empty");
            }
            if (!userDetails.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new RuntimeException("Invalid email format");
            }
            
            // Check duplicate email (excluding current user)
            Optional<UserProfile> existingUser = userProfileRepository.findByEmail(userDetails.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(userDetails.getEmail());
        }
        
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userProfileRepository.save(user);
    }
}