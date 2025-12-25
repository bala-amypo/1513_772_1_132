package com.example.demo.repository;

import com.example.demo.model.SkillOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillOfferRepository extends JpaRepository<SkillOffer, Long> {
    List<SkillOffer> findByUserId(Long userId);
}
