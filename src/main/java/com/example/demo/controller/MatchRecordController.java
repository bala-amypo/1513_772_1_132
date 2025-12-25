package com.example.demo.controller;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchRecordController {

    private final MatchmakingService service;

    public MatchRecordController(MatchmakingService service) {
        this.service = service;
    }

    @PostMapping("/{requestId}")
    public ResponseEntity<MatchRecord> generate(@PathVariable Long requestId) {
        return ResponseEntity.ok(service.generateMatch(requestId));
    }
}
