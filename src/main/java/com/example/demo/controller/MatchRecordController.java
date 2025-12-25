package com.example.demo.controller;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match-records")
public class MatchRecordController {
    
    @Autowired
    private MatchmakingService matchmakingService;
    
    @PostMapping("/generate/{userId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.generateMatch(userId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.getMatchesForUser(userId));
    }
}