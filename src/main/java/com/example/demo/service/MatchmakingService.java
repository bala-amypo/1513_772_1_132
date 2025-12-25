package com.example.demo.service;

import com.example.demo.model.MatchRecord;
import java.util.List;

public interface MatchmakingService {
    MatchRecord generateMatch(Long requestId);
    List<MatchRecord> getMatchesForUser(Long userId);
}
