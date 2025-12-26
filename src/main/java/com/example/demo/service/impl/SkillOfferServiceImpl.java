package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.SkillOffer;
import com.example.demo.repository.SkillOfferRepository;
import com.example.demo.service.SkillOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkillOfferServiceImpl implements SkillOfferService {
    
    @Autowired
    private SkillOfferRepository skillOfferRepository;
    
    @Override
    public SkillOffer createOffer(SkillOffer offer) {
        // Validate required fields
        if (offer.getUser() == null || offer.getUser().getId() == null) {
            throw new ValidationException("User is required for skill offer");
        }
        if (offer.getSkill() == null || offer.getSkill().getId() == null) {
            throw new ValidationException("Skill is required for skill offer");
        }
        if (offer.getExperienceLevel() == null || offer.getExperienceLevel().trim().isEmpty()) {
            throw new ValidationException("Experience level is required");
        }
        
        offer.setActive(true);
        return skillOfferRepository.save(offer);
    }
    
    @Override
    public SkillOffer getOfferById(Long id) {
        Optional<SkillOffer> offer = skillOfferRepository.findById(id);
        if (offer.isPresent()) {
            if (!offer.get().isActive()) {
                throw new ResourceInactiveException("SkillOffer with ID " + id + " is deactivated");
            }
            return offer.get();
        }
        throw new ResourceNotFoundException("SkillOffer not found with id: " + id);
    }
    
    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return skillOfferRepository.findByUserId(userId).stream()
            .filter(SkillOffer::isActive)
            .toList();
    }
    
    public List<SkillOffer> getAllOffers() {
        return skillOfferRepository.findAll();
    }
    
    public List<SkillOffer> getActiveOffers() {
        return skillOfferRepository.findAll().stream()
            .filter(SkillOffer::isActive)
            .toList();
    }
    
    public SkillOffer updateOffer(Long id, SkillOffer offerDetails) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
            
        if (!offer.isActive()) {
            throw new ResourceInactiveException("Cannot update deactivated skill offer with ID " + id);
        }
        
        if (offerDetails.getExperienceLevel() != null) {
            offer.setExperienceLevel(offerDetails.getExperienceLevel());
        }
        
        return skillOfferRepository.save(offer);
    }
    
    public void deleteOffer(Long id) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
        
        skillOfferRepository.delete(offer);
    }
    
    public void deactivateOffer(Long id) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
            
        if (!offer.isActive()) {
            throw new ResourceInactiveException("SkillOffer with ID " + id + " is already deactivated");
        }
        
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }
}