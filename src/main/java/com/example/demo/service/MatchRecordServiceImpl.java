package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.MatchRecord;
import com.example.demo.repository.MatchRecordRepository;

@Service
public class MatchRecordServiceImpl implements MatchRecordService {

    private final MatchRecordRepository repository;

    public MatchRecordServiceImpl(MatchRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public MatchRecord generateMatch(Long userId) {
        MatchRecord match = new MatchRecord();
        match.setStatus("PENDING");
        match.setMatchedAt(LocalDateTime.now());
        return repository.save(match);
    }

    @Override
    public MatchRecord getMatchById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<MatchRecord> getMatchesForUser(Long userId) {
        List<MatchRecord> result = new ArrayList<>();
        result.addAll(repository.findByUserAId(userId));
        result.addAll(repository.findByUserBId(userId));
        return result;
    }

    @Override
    public MatchRecord updateMatchStatus(Long id, String status) {
        MatchRecord match = repository.findById(id).orElse(null);
        if (match != null) {
            match.setStatus(status);
            return repository.save(match);
        }
        return null;
    }
}
