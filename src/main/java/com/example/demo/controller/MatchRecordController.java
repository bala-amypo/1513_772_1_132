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
    
    @PostMapping
    public ResponseEntity<MatchRecord> create(@RequestBody MatchRecord match) {
        return ResponseEntity.ok(((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).createMatch(match));
    }
    
    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAll() {
        return ResponseEntity.ok(((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).getAllMatches());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchRecord> getById(@PathVariable Long id) {
        MatchRecord match = ((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).getMatchById(id);
        return ResponseEntity.ok(match);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MatchRecord> update(@PathVariable Long id, @RequestBody MatchRecord matchDetails) {
        MatchRecord updated = ((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).updateMatch(id, matchDetails);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ((com.example.demo.service.impl.MatchmakingServiceImpl) matchmakingService).deleteMatch(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/generate/{userId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.generateMatch(userId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchRecord>> getForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(matchmakingService.getMatchesForUser(userId));
    }
}