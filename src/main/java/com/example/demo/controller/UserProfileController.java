package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-profiles")
@Validated  // Add this annotation
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @PostMapping
    public ResponseEntity<UserProfile> create(@Valid @RequestBody UserProfile user) {
        // Add @Valid annotation
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
    public ResponseEntity<UserProfile> update(@PathVariable Long id, @Valid @RequestBody UserProfile user) {
        // Add @Valid annotation
        UserProfile updated = ((com.example.demo.service.impl.UserProfileServiceImpl) userProfileService).updateUser(id, user);
        return ResponseEntity.ok(updated);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        userProfileService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
}