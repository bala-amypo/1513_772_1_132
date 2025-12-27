package com.example.demo.service.impl;

import com.example.demo.exception.EmailAlreadyInUseException;
import com.example.demo.model.AppUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public AppUser findByEmail(String email) {
        Optional<AppUser> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
    
    @Override
    public AppUser save(AppUser user) {
        Optional<AppUser> existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser.isPresent()) {
            AppUser existing = existingUser.get();
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
            existing.setRole(user.getRole());
            existing.setActive(true);
            return userRepository.save(existing);
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        
        user.setActive(true);
        return userRepository.save(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        
        AppUser user = userOpt.get();
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}