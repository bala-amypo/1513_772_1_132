package com.example.demo.service.impl;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public AppUser findByEmail(String email) {
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setRole("ADMIN");
        user.setId(1L);
        return user;
    }
    
    @Override
    public AppUser save(AppUser user) {
        return user;
    }
}