package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skill-requests")
public class SkillRequestController {
    
    @Autowired
    private SkillRequestService skillRequestService;
    
    @PostMapping
    public ResponseEntity<SkillRequest> create(@RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillRequestService.createRequest(request));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> get(@PathVariable Long id) {
        return ResponseEntity.ok(skillRequestService.getRequestById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<SkillRequest>> getAll() {
        return ResponseEntity.ok(((com.example.demo.service.impl.SkillRequestServiceImpl) skillRequestService).getAllRequests());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillRequest>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(skillRequestService.getRequestsByUser(userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SkillRequest> update(@PathVariable Long id, @RequestBody SkillRequest request) {
        SkillRequest updated = ((com.example.demo.service.impl.SkillRequestServiceImpl) skillRequestService).updateRequest(id, request);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ((com.example.demo.service.impl.SkillRequestServiceImpl) skillRequestService).deleteRequest(id);
        return ResponseEntity.ok().build();
    }
}