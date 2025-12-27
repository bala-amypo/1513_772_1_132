package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MatchRecord;
import com.example.demo.repository.MatchRecordRepository;
import com.example.demo.service.MatchmakingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {

    @Autowired
    private MatchRecordRepository matchRecordRepository;

    @Override
    public MatchRecord generateMatch(Long userId) {
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        return matchRecordRepository.save(match);
    }

    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        // TODO: Implement real filtering by userId
        return new ArrayList<>();
    }

    @Override
    public MatchRecord createMatch(MatchRecord match) {
        if (match.getStatus() == null) {
            match.setStatus("PENDING");
        }
        return matchRecordRepository.save(match);
    }

    @Override
    public List<MatchRecord> getAllMatches() {
        return matchRecordRepository.findAll();
    }

    @Override
    public MatchRecord getMatchById(Long id) {
        return matchRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
    }

    @Override
    public MatchRecord updateMatch(Long id, MatchRecord matchDetails) {
        MatchRecord match = getMatchById(id);
        if (matchDetails.getStatus() != null) {
            match.setStatus(matchDetails.getStatus());
        }
        return matchRecordRepository.save(match);
    }
}
