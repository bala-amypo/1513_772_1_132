package com.example.demo.service;

import java.util.List;
import com.example.demo.model.MatchRecord;

public interface MatchRecordService {

    MatchRecord generateMatch(Long userId);

    MatchRecord getMatchById(Long id);

    List<MatchRecord> getMatchesForUser(Long userId);

    MatchRecord updateMatchStatus(Long id, String status);
}
