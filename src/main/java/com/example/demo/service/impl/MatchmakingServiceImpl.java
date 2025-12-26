package com.example.demo.service.impl;

import com.example.demo.model.MatchRecord;
import com.example.demo.model.SkillOffer;
import com.example.demo.model.SkillRequest;
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
    
    @Autowired
    private SkillOfferServiceImpl skillOfferService;
    
    @Autowired
    private SkillRequestServiceImpl skillRequestService;
    
    @Override
    public MatchRecord generateMatch(Long userId) {
        // Simple matching algorithm
        List<SkillOffer> userOffers = skillOfferService.getOffersByUser(userId);
        List<SkillRequest> userRequests = skillRequestService.getRequestsByUser(userId);
        
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        
        if (!userOffers.isEmpty()) {
            match.setUserA(userOffers.get(0).getUser());
            match.setSkillOfferedByA(userOffers.get(0).getSkill());
        }
        
        // Find matching user (simplified logic)
        List<SkillOffer> allOffers = skillOfferService.getAllOffers();
        List<SkillRequest> allRequests = skillRequestService.getAllRequests();
        
        // Simple match logic - in real app this would be more complex
        for (SkillRequest request : allRequests) {
            if (request.getUser() != null && !request.getUser().getId().equals(userId)) {
                for (SkillOffer offer : userOffers) {
                    if (offer.getSkill() != null && request.getSkill() != null &&
                        offer.getSkill().getId().equals(request.getSkill().getId())) {
                        match.setUserB(request.getUser());
                        match.setSkillOfferedByB(offer.getSkill());
                        break;
                    }
                }
            }
        }
        
        return matchRecordRepository.save(match);
    }
    
    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        return matchRecordRepository.findAll().stream()
            .filter(match -> (match.getUserA() != null && match.getUserA().getId().equals(userId)) ||
                           (match.getUserB() != null && match.getUserB().getId().equals(userId)))
            .toList();
    }
    
    public MatchRecord createMatch(MatchRecord match) {
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
        throw new RuntimeException("MatchRecord not found with id: " + id);
    }
    
    public MatchRecord updateMatch(Long id, MatchRecord matchDetails) {
        MatchRecord match = getMatchById(id);
        
        if (matchDetails.getStatus() != null) {
            match.setStatus(matchDetails.getStatus());
        }
        if (matchDetails.getUserA() != null) {
            match.setUserA(matchDetails.getUserA());
        }
        if (matchDetails.getUserB() != null) {
            match.setUserB(matchDetails.getUserB());
        }
        if (matchDetails.getSkillOfferedByA() != null) {
            match.setSkillOfferedByA(matchDetails.getSkillOfferedByA());
        }
        if (matchDetails.getSkillOfferedByB() != null) {
            match.setSkillOfferedByB(matchDetails.getSkillOfferedByB());
        }
        
        return matchRecordRepository.save(match);
    }
    
    public void deleteMatch(Long id) {
        MatchRecord match = getMatchById(id);
        matchRecordRepository.delete(match);
    }
    
    public List<MatchRecord> getMatchesByStatus(String status) {
        return matchRecordRepository.findAll().stream()
            .filter(match -> status.equals(match.getStatus()))
            .toList();
    }
    
    public MatchRecord updateMatchStatus(Long id, String status) {
        MatchRecord match = getMatchById(id);
        match.setStatus(status);
        return matchRecordRepository.save(match);
    }
}