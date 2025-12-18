package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userAId;
    private Long userBId;
    private Long skillOfferedByAId;
    private Long skillOfferedByBId;
    private LocalDateTime matchedAt = LocalDateTime.now();
    private String status = "PENDING";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserAId() { return userAId; }
    public void setUserAId(Long userAId) { this.userAId = userAId; }
    public Long getUserBId() { return userBId; }
    public void setUserBId(Long userBId) { this.userBId = userBId; }
    public Long getSkillOfferedByAId() { return skillOfferedByAId; }
    public void setSkillOfferedByAId(Long skillOfferedByAId) { this.skillOfferedByAId = skillOfferedByAId; }
    public Long getSkillOfferedByBId() { return skillOfferedByBId; }
    public void setSkillOfferedByBId(Long skillOfferedByBId) { this.skillOfferedByBId = skillOfferedByBId; }
    public LocalDateTime getMatchedAt() { return matchedAt; }
    public void setMatchedAt(LocalDateTime matchedAt) { this.matchedAt = matchedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


--------


---------------

------------


--------------

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
