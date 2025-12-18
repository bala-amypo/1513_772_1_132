package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MatchRecord;

public interface MatchRecordRepository extends JpaRepository<MatchRecord, Long> {

    List<MatchRecord> findByUserA_Id(Long userId);

    List<MatchRecord> findByUserB_Id(Long userId);
}
