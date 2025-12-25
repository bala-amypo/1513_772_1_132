package com.example.demo.service.impl;

import com.example.demo.model.MatchRecord;
import com.example.demo.repository.MatchRecordRepository;
import com.example.demo.service.MatchmakingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {

    private final MatchRecordRepository repo;

    public MatchmakingServiceImpl(MatchRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public MatchRecord generateMatch(Long requestId) {
        MatchRecord m = new MatchRecord();
        m.setStatus("PENDING");
        return m;
    }

    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        return repo.findByUserAIdOrUserBId(userId, userId);
    }
}
