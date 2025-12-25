package com.example.demo.service.impl;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repo;

    public UserProfileServiceImpl(UserProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserProfile createUser(UserProfile user) {
        return repo.save(user);
    }

    @Override
    public UserProfile getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProfile not found"));
    }

    @Override
    public void deactivateUser(Long id) {
        UserProfile u = getUserById(id);
        u.setActive(false);
        repo.save(u);
    }
}
