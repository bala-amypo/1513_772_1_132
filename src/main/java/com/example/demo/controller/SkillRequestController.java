package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;

@RestController
@RequestMapping("/api/skill-requests")
public class SkillRequestController {

    private final SkillRequestService service;

    public SkillRequestController(SkillRequestService service) {
        this.service = service;
    }

    @PostMapping
    public SkillRequest create(@RequestBody SkillRequest request) {
        return service.createRequest(request);
    }

    @PutMapping("/{id}")
    public SkillRequest update(@PathVariable Long id, @RequestBody SkillRequest request) {
        return service.updateRequest(id, request);
    }

    @GetMapping("/{id}")
    public SkillRequest getById(@PathVariable Long id) {
        return service.getRequestById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SkillRequest> getByUser(@PathVariable Long userId) {
        return service.getRequestsByUser(userId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRequest(id);
    }
}

