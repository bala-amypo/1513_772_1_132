package com.example.demo.controller;

import com.example.demo.model.SkillMatch;
import com.example.demo.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<SkillMatch> createMatch(
            @RequestParam Long offerId,
            @RequestParam Long requestId) {
        return ResponseEntity.ok(matchService.createMatch(offerId, requestId));
    }

    @GetMapping
    public ResponseEntity<List<SkillMatch>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillMatch> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SkillMatch> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(matchService.updateMatchStatus(id, status));
    }

    @GetMapping("/offer/{offerId}")
    public ResponseEntity<List<SkillMatch>> getByOffer(@PathVariable Long offerId) {
        return ResponseEntity.ok(matchService.getMatchesByOffer(offerId));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<List<SkillMatch>> getByRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(matchService.getMatchesByRequest(requestId));
    }
}
