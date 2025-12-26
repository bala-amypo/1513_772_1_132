package com.example.demo.service.impl;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private List<AppUser> users = new ArrayList<>();
    
    public UserServiceImpl() {
        // Initialize with test users
        AppUser admin = new AppUser();
        admin.setId(1L);
        admin.setEmail("admin@mail.com");
        admin.setPassword("admin123");
        admin.setRole("ADMIN");
        
        AppUser user = new AppUser();
        user.setId(2L);
        user.setEmail("user@mail.com");
        user.setPassword("user123");
        user.setRole("USER");
        
        users.add(admin);
        users.add(user);
    }
    
    @Override
    public AppUser findByEmail(String email) {
        Optional<AppUser> user = users.stream()
            .filter(u -> email.equals(u.getEmail()))
            .findFirst();
        
        if (user.isPresent()) {
            return user.get();
        }
        
        // Return default user if not found
        AppUser defaultUser = new AppUser();
        defaultUser.setId(99L);
        defaultUser.setEmail(email);
        defaultUser.setRole("USER");
        return defaultUser;
    }
    
    @Override
    public AppUser save(AppUser user) {
        users.add(user);
        return user;
    }
    
    public List<AppUser> getAllUsers() {
        return users;
    }
    
    public AppUser getUserById(Long id) {
        return users.stream()
            .filter(u -> id.equals(u.getId()))
            .findFirst()
            .orElse(null);
    }
    
    public void deleteUser(Long id) {
        users.removeIf(u -> id.equals(u.getId()));
    }
}