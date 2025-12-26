package com.example.demo.service.impl;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        // Check if username already exists
        if (userProfileRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        
        // Check if email already exists
        if (userProfileRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
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
        
        // Check if username is being changed and if it already exists
        if (userDetails.getUsername() != null && 
            !userDetails.getUsername().equals(user.getUsername()) &&
            userProfileRepository.existsByUsername(userDetails.getUsername())) {
            throw new RuntimeException("Username already exists: " + userDetails.getUsername());
        }
        
        // Check if email is being changed and if it already exists
        if (userDetails.getEmail() != null && 
            !userDetails.getEmail().equals(user.getEmail()) &&
            userProfileRepository.existsByEmail(userDetails.getEmail())) {
            throw new RuntimeException("Email already exists: " + userDetails.getEmail());
        }
        
        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userProfileRepository.save(user);
    }
}