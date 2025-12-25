package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class SkillRequestController {

    private final SkillRequestService skillRequestService;

    public SkillRequestController(SkillRequestService skillRequestService) {
        this.skillRequestService = skillRequestService;
    }

    @PostMapping
    public ResponseEntity<SkillRequest> create(@RequestBody SkillRequest req) {
        return ResponseEntity.ok(skillRequestService.createRequest(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> get(@PathVariable Long id) {
        return ResponseEntity.ok(skillRequestService.getRequestById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillRequest>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(skillRequestService.getRequestsByUser(userId));
    }
}
