package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @PostMapping
    public ResponseEntity<UserProfile> create(@Valid @RequestBody UserProfile user) {
        UserProfile createdUser = userProfileService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> get(@PathVariable Long id) {
        UserProfile user = userProfileService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Long id, @Valid @RequestBody UserProfile userDetails) {
        UserProfile updatedUser = userProfileService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        userProfileService.deactivateUser(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deactivated successfully");
        response.put("status", "SUCCESS");
        response.put("userId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<UserProfile>> list() {
        List<UserProfile> users = userProfileService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}