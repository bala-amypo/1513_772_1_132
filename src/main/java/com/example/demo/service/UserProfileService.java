package com.example.demo.service;

import com.example.demo.model.UserProfile;
import java.util.List;

public interface UserProfileService {
    UserProfile createUser(UserProfile user);
    UserProfile getUserById(Long id);
    void deactivateUser(Long id);
}