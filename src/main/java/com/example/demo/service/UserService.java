package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    User getById(Long id);

    User updateRating(Long userId, double newRating);
}
