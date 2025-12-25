package com.example.demo.controller;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class SkillRequestController {

    private final SkillRequestService service;

    public SkillRequestController(SkillRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SkillRequest> create(@RequestBody SkillRequest request) {
        return ResponseEntity.ok(service.createRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillRequest> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRequestById(id));
    }
}
