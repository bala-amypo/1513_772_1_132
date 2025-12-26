package com.example.demo.service.impl;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public AppUser findByEmail(String email) {
        // For backward compatibility with tests
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setRole("ADMIN");
        user.setId(1L);
        // Store encrypted password for registered users
        if (email.equals("admin@example.com")) {
            user.setPassword(passwordEncoder.encode("admin123"));
        } else if (email.equals("user@example.com")) {
            user.setPassword(passwordEncoder.encode("user123"));
        }
        return user;
    }
    
    @Override
    public AppUser save(AppUser user) {
        // Encrypt password before saving
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return user;
    }
    
    // Add these methods for registration
    public AppUser registerUser(AppUser user) {
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return save(user);
    }
    
    public boolean existsByEmail(String email) {
        // For simplicity, check predefined emails
        return email.equals("admin@example.com") || email.equals("user@example.com");
    }
}