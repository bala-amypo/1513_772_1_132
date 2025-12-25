package com.example.demo.repository;

import com.example.demo.model.MatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRecordRepository extends JpaRepository<MatchRecord, Long> {
    List<MatchRecord> findByUserAIdOrUserBId(Long userAId, Long userBId);
}
