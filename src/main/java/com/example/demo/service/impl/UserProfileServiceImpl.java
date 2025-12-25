package com.example.demo.service.impl;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final Map<Long, UserProfile> db = new HashMap<>();
    private long idCounter = 1;

    @Override
    public UserProfile createUser(UserProfile profile) {
        profile.setId(idCounter++);
        db.put(profile.getId(), profile);
        return profile;
    }

    @Override
    public UserProfile getUserById(Long id) {
        if (!db.containsKey(id)) throw new RuntimeException("UserProfile not found");
        return db.get(id);
    }

    @Override
    public void deactivateUser(Long id) {
        UserProfile u = getUserById(id);
        u.setActive(false);
    }
}
