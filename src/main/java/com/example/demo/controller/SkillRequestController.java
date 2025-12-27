package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
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
@RequestMapping("/api/skill-requests")
@Validated
public class SkillRequestController {
    
    @Autowired
    private SkillRequestService skillRequestService;
    
    @PostMapping
    public ResponseEntity<SkillRequest> create(@Valid @RequestBody SkillRequest request) {
        SkillRequest createdRequest = skillRequestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> get(@PathVariable Long id) {
        SkillRequest request = skillRequestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SkillRequest> update(@PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        SkillRequest updatedRequest = skillRequestService.updateRequest(id, request);
        return ResponseEntity.ok(updatedRequest);
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        skillRequestService.deactivateRequest(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Skill request deactivated successfully");
        response.put("status", "SUCCESS");
        response.put("requestId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<SkillRequest>> getAll() {
        List<SkillRequest> requests = skillRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillRequest>> getByUser(@PathVariable Long userId) {
        List<SkillRequest> requests = skillRequestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }
}