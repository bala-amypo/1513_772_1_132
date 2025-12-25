package com.example.demo.service.impl;

import com.example.demo.model.MatchRecord;
import com.example.demo.service.MatchmakingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {

    private final List<MatchRecord> matches = new ArrayList<>();

    @Override
    public MatchRecord generateMatch(Long userId) {
        MatchRecord m = new MatchRecord();
        matches.add(m);
        return m;
    }

    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        return matches;
    }
}
