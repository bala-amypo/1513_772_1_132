package com.example.demo.controller;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchRecordController {
    
    @Autowired
    private MatchmakingService matchmakingService;
    
    @PostMapping("/generate/{userId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long userId) {
        MatchRecord match = matchmakingService.generateMatch(userId);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<MatchRecord> create(@RequestBody MatchRecord match) {
        MatchRecord createdMatch = matchmakingService.createMatch(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchRecord> get(@PathVariable Long id) {
        MatchRecord match = matchmakingService.getMatchById(id);
        return ResponseEntity.ok(match);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MatchRecord> update(@PathVariable Long id, @RequestBody MatchRecord matchDetails) {
        MatchRecord updatedMatch = matchmakingService.updateMatch(id, matchDetails);
        return ResponseEntity.ok(updatedMatch);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matchmakingService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<List<MatchRecord>> list() {
        List<MatchRecord> matches = matchmakingService.getAllMatches();
        return ResponseEntity.ok(matches);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchRecord>> getForUser(@PathVariable Long userId) {
        List<MatchRecord> matches = matchmakingService.getMatchesForUser(userId);
        return ResponseEntity.ok(matches);
    }
}