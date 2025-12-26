package com.example.demo.controller;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/match-records")
public class MatchRecordController {
    
    @Autowired
    private MatchmakingService matchmakingService;
    
    @PostMapping("/generate/{userId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.generateMatch(userId));
    }
    
    @PostMapping
    public ResponseEntity<MatchRecord> create(@RequestBody MatchRecord match) {
        return ResponseEntity.ok(((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).createMatch(match));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).getMatchById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAll() {
        return ResponseEntity.ok(((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).getAllMatches());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchRecord>> getForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.getMatchesForUser(userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MatchRecord> update(@PathVariable Long id, @RequestBody MatchRecord match) {
        MatchRecord updated = ((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).updateMatch(id, match);
        return ResponseEntity.ok(updated);
    }
    
    
}