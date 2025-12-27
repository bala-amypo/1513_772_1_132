package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile createUser(UserProfile user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setActive(true);
        return userProfileRepository.save(user);
    }

    @Override
    public UserProfile getUserById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found  " )
                );
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile updateUser(Long id, UserProfile userDetails) {
        UserProfile user = getUserById(id);
        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userProfileRepository.save(user);
    }

    @Override
    public void deactivateUser(Long id) {
        UserProfile user = getUserById(id);
        user.setActive(false);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userProfileRepository.save(user);
    }
}
