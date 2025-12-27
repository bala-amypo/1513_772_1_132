package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillOffer;
import com.example.demo.repository.SkillOfferRepository;
import com.example.demo.service.SkillOfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillOfferServiceImpl implements SkillOfferService {

    @Autowired
    private SkillOfferRepository skillOfferRepository;

    @Override
    public SkillOffer createOffer(SkillOffer offer) {
        if (offer.getUser() == null || offer.getUser().getId() == null) {
            throw new IllegalArgumentException("User is required");
        }
        if (offer.getSkill() == null || offer.getSkill().getId() == null) {
            throw new IllegalArgumentException("Skill is required");
        }
        if (offer.getExperienceLevel() == null || offer.getExperienceLevel().trim().isEmpty()) {
            throw new IllegalArgumentException("Experience level is required");
        }
        
        String experienceLevel = offer.getExperienceLevel().toUpperCase();
        if (!experienceLevel.matches("^(BEGINNER|INTERMEDIATE|ADVANCED|EXPERT)$")) {
            throw new IllegalArgumentException("Experience level must be BEGINNER, INTERMEDIATE, ADVANCED, or EXPERT");
        }
        offer.setExperienceLevel(experienceLevel);
        
        offer.setActive(true);
        return skillOfferRepository.save(offer);
    }

    @Override
    public SkillOffer getOfferById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill offer ID");
        }
        return skillOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill offer not found with id: " + id));
    }

    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return skillOfferRepository.findByUserId(userId);
    }

    @Override
    public List<SkillOffer> getAllOffers() {
        return skillOfferRepository.findAll();
    }

    @Override
    public SkillOffer updateOffer(Long id, SkillOffer offerDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill offer ID");
        }
        
        SkillOffer offer = getOfferById(id);
        
        if (offerDetails.getExperienceLevel() != null) {
            String experienceLevel = offerDetails.getExperienceLevel().toUpperCase().trim();
            if (experienceLevel.isEmpty()) {
                throw new IllegalArgumentException("Experience level cannot be empty");
            }
            if (!experienceLevel.matches("^(BEGINNER|INTERMEDIATE|ADVANCED|EXPERT)$")) {
                throw new IllegalArgumentException("Experience level must be BEGINNER, INTERMEDIATE, ADVANCED, or EXPERT");
            }
            offer.setExperienceLevel(experienceLevel);
        }
        return skillOfferRepository.save(offer);
    }

    @Override
    public void deactivateOffer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill offer ID");
        }
        
        SkillOffer offer = getOfferById(id);
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }
}