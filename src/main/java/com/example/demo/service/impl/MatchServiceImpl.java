package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillMatch;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository repo;

    public MatchServiceImpl(MatchRepository repo) {
        this.repo = repo;
    }

    @Override
    public SkillMatch createMatch(Long offerId, Long requestId) {
        return repo.save(new SkillMatch(offerId, requestId, "PENDING"));
    }

    @Override
    public SkillMatch getMatchById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }

    @Override
    public List<SkillMatch> getAllMatches() {
        return repo.findAll();
    }

    @Override
    public SkillMatch updateMatchStatus(Long matchId, String status) {
        SkillMatch match = getMatchById(matchId);
        match.setStatus(status);
        return repo.save(match);
    }

    @Override
    public List<SkillMatch> getMatchesByOffer(Long offerId) {
        return repo.findByOfferId(offerId);
    }

    @Override
    public List<SkillMatch> getMatchesByRequest(Long requestId) {
        return repo.findByRequestId(requestId);
    }
}
