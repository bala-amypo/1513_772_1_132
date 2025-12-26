package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface UserService {
    AppUser findByEmail(String email);
    AppUser save(AppUser user);
    AppUser registerUser(AppUser user); // Add this method
    boolean existsByEmail(String email); // Add this method
}