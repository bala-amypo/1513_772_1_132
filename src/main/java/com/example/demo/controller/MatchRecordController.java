package com.example.demo.controller;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/match-records")
@Validated
public class MatchRecordController {
    
    @Autowired
    private MatchmakingService matchmakingService;
    
    @PostMapping("/generate/{userId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long userId) {
        MatchRecord match = matchmakingService.generateMatch(userId);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<MatchRecord> create(@Valid @RequestBody MatchRecord match) {
        MatchRecord createdMatch = matchmakingService.createMatch(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchRecord> get(@PathVariable Long id) {
        MatchRecord match = matchmakingService.getMatchById(id);
        return ResponseEntity.ok(match);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MatchRecord> update(@PathVariable Long id, @Valid @RequestBody MatchRecord match) {
        MatchRecord updatedMatch = matchmakingService.updateMatch(id, match);
        return ResponseEntity.ok(updatedMatch);
    }
    
    @PostMapping("/{id}/complete")
    public ResponseEntity<Map<String, String>> complete(@PathVariable Long id) {
        MatchRecord match = matchmakingService.getMatchById(id);
        match.setStatus("COMPLETED");
        matchmakingService.updateMatch(id, match);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Match marked as completed successfully");
        response.put("status", "SUCCESS");
        response.put("matchId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Map<String, String>> cancel(@PathVariable Long id) {
        MatchRecord match = matchmakingService.getMatchById(id);
        match.setStatus("CANCELLED");
        matchmakingService.updateMatch(id, match);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Match cancelled successfully");
        response.put("status", "SUCCESS");
        response.put("matchId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAll() {
        List<MatchRecord> matches = matchmakingService.getAllMatches();
        return ResponseEntity.ok(matches);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchRecord>> getForUser(@PathVariable Long userId) {
        List<MatchRecord> matches = matchmakingService.getMatchesForUser(userId);
        return ResponseEntity.ok(matches);
    }
}