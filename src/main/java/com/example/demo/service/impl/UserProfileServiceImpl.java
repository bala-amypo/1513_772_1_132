package com.example.demo.service.impl;

import com.example.demo.model.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public UserProfile createUser(UserProfile user) {
        // Implementation would use repository
        return user;
    }
    
    @Override
    public UserProfile getUserById(Long id) {
        // Implementation would use repository
        UserProfile user = new UserProfile();
        user.setId(id);
        return user;
    }
    
    @Override
    public void deactivateUser(Long id) {
        // Implementation would deactivate user
    }
}