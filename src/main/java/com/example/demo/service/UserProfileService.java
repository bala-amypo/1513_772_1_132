package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserProfile;

public interface UserProfileService {
    UserProfile createUser(UserProfile user);
    UserProfile updateUser(Long id, UserProfile user);
    UserProfile getUserById(Long id);
    List<UserProfile> getAllUsers();
    void deactivateUser(Long id);
}
