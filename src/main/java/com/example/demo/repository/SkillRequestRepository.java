package com.example.demo.repository;

import com.example.demo.model.SkillRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRequestRepository extends JpaRepository<SkillRequest, Long> {
    List<SkillRequest> findByUserId(Long userId);
    List<SkillRequest> findByCategoryId(Long categoryId);
    List<SkillRequest> findByOpenTrue();
}
