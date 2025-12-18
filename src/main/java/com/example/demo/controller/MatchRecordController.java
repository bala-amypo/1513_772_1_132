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

    @PostMapping
    public MatchRecord create(@RequestBody MatchRecord record) {
        return service.createMatch(record);
    }

    @PutMapping("/{id}")
    public MatchRecord update(@PathVariable Long id,
                              @RequestBody MatchRecord record) {
        return service.updateMatch(id, record);
    }

    @GetMapping("/{id}")
    public MatchRecord getById(@PathVariable Long id) {
        return service.getMatchById(id);
    }

    @GetMapping("/user/{userId}")
    public List<MatchRecord> getByUser(@PathVariable Long userId) {
        return service.getMatchesByUser(userId);
    }
}

