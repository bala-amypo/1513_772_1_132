package com.example.demo.service.impl;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {
    @Override
    public MatchRecord generateMatch(Long userId) {
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        return match;
    }
    
    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        return new ArrayList<>();
    }
}