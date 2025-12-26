package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @PostMapping
    public ResponseEntity<UserProfile> create(@RequestBody UserProfile user) {
        return ResponseEntity.ok(userProfileService.createUser(user));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> get(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<UserProfile>> getAll() {
        return ResponseEntity.ok(((com.example.demo.service.impl.UserProfileServiceImpl) userProfileService).getAllUsers());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Long id, @RequestBody UserProfile user) {
        UserProfile updated = ((com.example.demo.service.impl.UserProfileServiceImpl) userProfileService).updateUser(id, user);
        return ResponseEntity.ok(updated);
    }
    
    
    
    
}