package com.example.demo.service;

import com.example.demo.model.UserProfile;
import java.util.List;

public interface UserProfileService {

    UserProfile createUser(UserProfile user);

    UserProfile getUserById(Long id);

    List<UserProfile> getAllUsers();

    UserProfile updateUser(Long id, UserProfile userDetails);

    void deactivateUser(Long id);
}
