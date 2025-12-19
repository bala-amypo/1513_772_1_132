package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchRecordService;

@RestController
@RequestMapping("/api/matches")
public class MatchRecordController {

    private final MatchRecordService service;

    public MatchRecordController(MatchRecordService service) {
        this.service = service;
    }

    // Generate match for a user
    @PostMapping("/generate/{userId}")
    public MatchRecord generateMatch(@PathVariable Long userId) {
        return service.generateMatch(userId);
    }

    // Get match by id
    @GetMapping("/{id}")
    public MatchRecord getMatchById(@PathVariable Long id) {
        return service.getMatchById(id);
    }

    // Get matches for a user
    @GetMapping("/user/{userId}")
    public List<MatchRecord> getMatchesForUser(@PathVariable Long userId) {
        return service.getMatchesForUser(userId);
    }

    // Update match status
    @PutMapping("/{id}/status")
    public MatchRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateMatchStatus(id, status);
    }
}
