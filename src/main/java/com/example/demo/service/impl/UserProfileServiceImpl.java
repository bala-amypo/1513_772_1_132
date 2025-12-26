package com.example.demo.service.impl;

import com.example.demo.exception.*;
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
        // Check if email already exists
        Optional<UserProfile> existingUser = userProfileRepository.findAll().stream()
            .filter(u -> user.getEmail() != null && user.getEmail().equals(u.getEmail()))
            .findFirst();
            
        if (existingUser.isPresent()) {
            throw new ResourceAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        
        // Validate required fields
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email is required");
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
            if (!user.get().isActive()) {
                throw new ResourceInactiveException("User with ID " + id + " is deactivated");
            }
            return user.get();
        }
        throw new ResourceNotFoundException("UserProfile not found with id: " + id);
    }
    
    @Override
    public void deactivateUser(Long id) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
            
        if (!user.isActive()) {
            throw new ResourceInactiveException("User with ID " + id + " is already deactivated");
        }
        
        user.setActive(false);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userProfileRepository.save(user);
    }
    
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }
    
    public List<UserProfile> getActiveUsers() {
        return userProfileRepository.findAll().stream()
            .filter(UserProfile::isActive)
            .toList();
    }
    
    public UserProfile updateUser(Long id, UserProfile userDetails) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
            
        if (!user.isActive()) {
            throw new ResourceInactiveException("Cannot update deactivated user with ID " + id);
        }
        
        if (userDetails.getUsername() != null && !userDetails.getUsername().trim().isEmpty()) {
            user.setUsername(userDetails.getUsername());
        }
        
        if (userDetails.getEmail() != null && !userDetails.getEmail().trim().isEmpty()) {
            // Check if email is being changed to an existing email
            if (!user.getEmail().equals(userDetails.getEmail())) {
                Optional<UserProfile> existingUser = userProfileRepository.findAll().stream()
                    .filter(u -> userDetails.getEmail().equals(u.getEmail()))
                    .findFirst();
                    
                if (existingUser.isPresent()) {
                    throw new ResourceAlreadyExistsException("Email " + userDetails.getEmail() + " already exists");
                }
                user.setEmail(userDetails.getEmail());
            }
        }
        
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userProfileRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
        
        userProfileRepository.delete(user);
    }
}