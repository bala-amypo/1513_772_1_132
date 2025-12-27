package com.example.demo.service;

import com.example.demo.model.MatchRecord;
import java.util.List;

public interface MatchmakingService {

    MatchRecord generateMatch(Long userId);

    List<MatchRecord> getMatchesForUser(Long userId);

    MatchRecord createMatch(MatchRecord match);

    List<MatchRecord> getAllMatches();

    MatchRecord getMatchById(Long id);

    MatchRecord updateMatch(Long id, MatchRecord matchDetails);
}
