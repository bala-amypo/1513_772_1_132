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
        // For backward compatibility
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setRole("ADMIN");
        user.setId(1L);
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
}