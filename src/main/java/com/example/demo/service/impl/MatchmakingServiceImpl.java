package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.MatchRecord;
import com.example.demo.repository.MatchRecordRepository;
import com.example.demo.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {
    
    @Autowired
    private MatchRecordRepository matchRecordRepository;
    
    @Override
    public MatchRecord generateMatch(Long userId) {
        // Validate user exists
        if (userId == null) {
            throw new ValidationException("User ID is required to generate match");
        }
        
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        return matchRecordRepository.save(match);
    }
    
    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        if (userId == null) {
            throw new ValidationException("User ID is required");
        }
        
        return matchRecordRepository.findAll().stream()
            .filter(match -> (match.getUserA() != null && match.getUserA().getId().equals(userId)) ||
                           (match.getUserB() != null && match.getUserB().getId().equals(userId)))
            .toList();
    }
    
    public MatchRecord createMatch(MatchRecord match) {
        if (match.getUserA() == null) {
            throw new ValidationException("User A is required for match");
        }
        if (match.getUserB() == null) {
            throw new ValidationException("User B is required for match");
        }
        if (match.getUserA().getId().equals(match.getUserB().getId())) {
            throw new ValidationException("User A and User B cannot be the same");
        }
        
        if (match.getStatus() == null) {
            match.setStatus("PENDING");
        }
        return matchRecordRepository.save(match);
    }
    
    public List<MatchRecord> getAllMatches() {
        return matchRecordRepository.findAll();
    }
    
    public MatchRecord getMatchById(Long id) {
        Optional<MatchRecord> match = matchRecordRepository.findById(id);
        if (match.isPresent()) {
            return match.get();
        }
        throw new ResourceNotFoundException("MatchRecord not found with id: " + id);
    }
    
    public MatchRecord updateMatch(Long id, MatchRecord matchDetails) {
        MatchRecord match = matchRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MatchRecord not found with id: " + id));
        
        if (matchDetails.getStatus() != null) {
            if (!isValidStatus(matchDetails.getStatus())) {
                throw new ValidationException("Invalid status. Allowed values: PENDING, ACCEPTED, REJECTED, COMPLETED");
            }
            match.setStatus(matchDetails.getStatus());
        }
        
        return matchRecordRepository.save(match);
    }
    
    public void deleteMatch(Long id) {
        MatchRecord match = matchRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MatchRecord not found with id: " + id));
        
        if (match.getStatus().equals("ACCEPTED")) {
            throw new OperationNotAllowedException("Cannot delete an accepted match. Change status first.");
        }
        
        matchRecordRepository.delete(match);
    }
    
    public MatchRecord updateMatchStatus(Long id, String status) {
        if (!isValidStatus(status)) {
            throw new ValidationException("Invalid status. Allowed values: PENDING, ACCEPTED, REJECTED, COMPLETED");
        }
        
        MatchRecord match = getMatchById(id);
        match.setStatus(status);
        return matchRecordRepository.save(match);
    }
    
    private boolean isValidStatus(String status) {
        return List.of("PENDING", "ACCEPTED", "REJECTED", "COMPLETED").contains(status);
    }
}