package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.OperationException;
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
    public ResponseEntity<?> generate(@PathVariable Long userId) {
        try {
            MatchRecord match = matchmakingService.generateMatch(userId);
            return new ResponseEntity<>(match, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match generation failed: " + e.getMessage());
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match generation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error during match generation");
        }
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MatchRecord match) {
        try {
            MatchRecord createdMatch = matchmakingService.createMatch(match);
            return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + e.getMessage());
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create match record");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            MatchRecord match = matchmakingService.getMatchById(id);
            return ResponseEntity.ok(match);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found with id: " + id);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot retrieve match: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving match details");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MatchRecord matchDetails) {
        try {
            MatchRecord updatedMatch = matchmakingService.updateMatch(id, matchDetails);
            return ResponseEntity.ok(updatedMatch);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found with id: " + id);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update match: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update match record");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            matchmakingService.deleteMatch(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found with id: " + id);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete match: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete match record");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MatchRecord>> list() {
        try {
            List<MatchRecord> matches = matchmakingService.getAllMatches();
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getForUser(@PathVariable Long userId) {
        try {
            List<MatchRecord> matches = matchmakingService.getMatchesForUser(userId);
            return ResponseEntity.ok(matches);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + userId);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot retrieve matches: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user matches");
        }
    }
}