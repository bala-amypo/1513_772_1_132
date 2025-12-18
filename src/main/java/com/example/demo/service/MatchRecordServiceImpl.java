package com.example.demo.service;

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
    public MatchRecord createMatch(MatchRecord record) {
        return repository.save(record);
    }

    @Override
    public MatchRecord updateMatch(Long id, MatchRecord record) {
        record.setId(id);
        return repository.save(record);
    }

    @Override
    public MatchRecord getMatchById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<MatchRecord> getMatchesByUser(Long userId) {
        List<MatchRecord> result = new ArrayList<>();
        result.addAll(repository.findByUserA_Id(userId));
        result.addAll(repository.findByUserB_Id(userId));
        return result;
    }
}
