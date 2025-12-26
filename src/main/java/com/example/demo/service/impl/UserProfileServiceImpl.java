package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.OperationException;
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
        // Keep test compatibility - just save and return
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
        throw new ResourceNotFoundException("UserProfile not found with id: " + id);
    }
    
    @Override
    public void deactivateUser(Long id) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
            
        if (!user.isActive()) {
            throw new OperationException("User with ID " + id + " is already deactivated");
        }
        
        user.setActive(false);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userProfileRepository.save(user);
    }
    
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }
    
    public UserProfile updateUser(Long id, UserProfile userDetails) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
            
        if (!user.isActive()) {
            throw new OperationException("Cannot update deactivated user with ID " + id);
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
    
    public void deleteUser(Long id) {
        UserProfile user = userProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id: " + id));
        
        if (!user.isActive()) {
            throw new OperationException("Cannot delete deactivated user. Deactivate first.");
        }
        
        userProfileRepository.delete(user);
    }
}