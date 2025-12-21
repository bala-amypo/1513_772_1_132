package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class SkillRequestController {

    private final SkillRequestService requestService;

    public SkillRequestController(SkillRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<SkillRequest> createRequest(@RequestBody SkillRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<SkillRequest>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> getRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillRequest>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByUser(userId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SkillRequest>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(requestService.getRequestsByCategory(categoryId));
    }

    @GetMapping("/open")
    public ResponseEntity<List<SkillRequest>> getOpenRequests() {
        return ResponseEntity.ok(requestService.getOpenRequests());
    }
}
