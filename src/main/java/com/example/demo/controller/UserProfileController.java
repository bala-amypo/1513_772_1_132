package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @PostMapping
    public ResponseEntity<UserProfile> create(@RequestBody UserProfile user) {
        UserProfile createdUser = userProfileService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> get(@PathVariable Long id) {
        UserProfile user = userProfileService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Long id, @RequestBody UserProfile userDetails) {
        UserProfile updatedUser = userProfileService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userProfileService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        userProfileService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<UserProfile>> list() {
        List<UserProfile> users = userProfileService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}