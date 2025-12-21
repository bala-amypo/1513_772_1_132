package com.example.demo.repository;

import com.example.demo.model.SkillMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<SkillMatch, Long> {
    List<SkillMatch> findByOfferId(Long offerId);
    List<SkillMatch> findByRequestId(Long requestId);
}
