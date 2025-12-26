package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class SkillRequestController {
    
    @Autowired
    private SkillRequestService skillRequestService;
    
    @PostMapping
    public ResponseEntity<SkillRequest> create(@RequestBody SkillRequest request) {
        SkillRequest createdRequest = skillRequestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> get(@PathVariable Long id) {
        SkillRequest request = skillRequestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SkillRequest> update(@PathVariable Long id, @RequestBody SkillRequest requestDetails) {
        SkillRequest updatedRequest = skillRequestService.updateRequest(id, requestDetails);
        return ResponseEntity.ok(updatedRequest);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        skillRequestService.deactivateRequest(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<SkillRequest>> list() {
        List<SkillRequest> requests = skillRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillRequest>> getByUser(@PathVariable Long userId) {
        List<SkillRequest> requests = skillRequestService.getRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }
}