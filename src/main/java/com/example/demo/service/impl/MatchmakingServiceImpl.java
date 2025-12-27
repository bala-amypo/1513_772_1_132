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
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        return matchRecordRepository.save(match);
    }

    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        
        List<MatchRecord> allMatches = matchRecordRepository.findAll();
        List<MatchRecord> userMatches = new ArrayList<>();
        
        for (MatchRecord match : allMatches) {
            if ((match.getUserA() != null && match.getUserA().getId().equals(userId)) ||
                (match.getUserB() != null && match.getUserB().getId().equals(userId))) {
                userMatches.add(match);
            }
        }
        
        return userMatches;
    }

    @Override
    public MatchRecord createMatch(MatchRecord match) {
        validateMatchRecord(match);
        
        if (match.getStatus() == null) {
            match.setStatus("PENDING");
        } else {
            String status = match.getStatus().toUpperCase();
            if (!status.matches("^(PENDING|APPROVED|REJECTED|COMPLETED|CANCELLED)$")) {
                throw new IllegalArgumentException("Status must be PENDING, APPROVED, REJECTED, COMPLETED, or CANCELLED");
            }
            match.setStatus(status);
        }
        return matchRecordRepository.save(match);
    }

    @Override
    public List<MatchRecord> getAllMatches() {
        return matchRecordRepository.findAll();
    }

    @Override
    public MatchRecord getMatchById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid match ID");
        }
        return matchRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
    }

    @Override
    public MatchRecord updateMatch(Long id, MatchRecord matchDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid match ID");
        }
        
        MatchRecord match = getMatchById(id);
        
        if (matchDetails.getStatus() != null) {
            String status = matchDetails.getStatus().toUpperCase().trim();
            if (status.isEmpty()) {
                throw new IllegalArgumentException("Status cannot be empty");
            }
            if (!status.matches("^(PENDING|APPROVED|REJECTED|COMPLETED|CANCELLED)$")) {
                throw new IllegalArgumentException("Status must be PENDING, APPROVED, REJECTED, COMPLETED, or CANCELLED");
            }
            match.setStatus(status);
        }
        
        if (matchDetails.getUserA() != null && matchDetails.getUserA().getId() != null) {
            match.setUserA(matchDetails.getUserA());
        }
        
        if (matchDetails.getUserB() != null && matchDetails.getUserB().getId() != null) {
            match.setUserB(matchDetails.getUserB());
        }
        
        if (matchDetails.getSkillOfferedByA() != null && matchDetails.getSkillOfferedByA().getId() != null) {
            match.setSkillOfferedByA(matchDetails.getSkillOfferedByA());
        }
        
        if (matchDetails.getSkillOfferedByB() != null && matchDetails.getSkillOfferedByB().getId() != null) {
            match.setSkillOfferedByB(matchDetails.getSkillOfferedByB());
        }
        
        return matchRecordRepository.save(match);
    }
    
    private void validateMatchRecord(MatchRecord match) {
        if (match.getUserA() == null || match.getUserA().getId() == null) {
            throw new IllegalArgumentException("User A is required");
        }
        if (match.getUserB() == null || match.getUserB().getId() == null) {
            throw new IllegalArgumentException("User B is required");
        }
        if (match.getSkillOfferedByA() == null || match.getSkillOfferedByA().getId() == null) {
            throw new IllegalArgumentException("Skill offered by User A is required");
        }
        if (match.getSkillOfferedByB() == null || match.getSkillOfferedByB().getId() == null) {
            throw new IllegalArgumentException("Skill offered by User B is required");
        }
        
        if (match.getUserA().getId().equals(match.getUserB().getId())) {
            throw new IllegalArgumentException("User A and User B cannot be the same");
        }
    }
}