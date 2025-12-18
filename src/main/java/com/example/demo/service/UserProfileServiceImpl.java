package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile createUser(UserProfile user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);
        return userProfileRepository.save(user);
    }

    @Override
    public UserProfile updateUser(Long id, UserProfile user) {
        UserProfile existingUser = userProfileRepository.findById(id).orElseThrow();

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setBio(user.getBio());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userProfileRepository.save(existingUser);
    }

    @Override
    public UserProfile getUserById(Long id) {
        return userProfileRepository.findById(id).orElseThrow();
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public void deactivateUser(Long id) {
        UserProfile user = userProfileRepository.findById(id).orElseThrow();
        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(user);
    }
}
