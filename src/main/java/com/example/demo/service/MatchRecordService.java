package com.example.demo.service;

import java.util.List;
import com.example.demo.model.MatchRecord;

public interface MatchRecordService {

    MatchRecord createMatch(MatchRecord record);

    MatchRecord updateMatch(Long id, MatchRecord record);

    MatchRecord getMatchById(Long id);

    List<MatchRecord> getMatchesByUser(Long userId);
}

