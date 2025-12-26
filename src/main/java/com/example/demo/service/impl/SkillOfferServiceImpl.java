package com.example.demo.service.impl;

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
        offer.setActive(true);
        return skillOfferRepository.save(offer);
    }
    
    @Override
    public SkillOffer getOfferById(Long id) {
        Optional<SkillOffer> offer = skillOfferRepository.findById(id);
        if (offer.isPresent()) {
            return offer.get();
        }
        throw new RuntimeException("SkillOffer not found with id: " + id);
    }
    
    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return skillOfferRepository.findAll().stream()
            .filter(offer -> offer.getUser() != null && offer.getUser().getId().equals(userId))
            .toList();
    }
    
    public List<SkillOffer> getAllOffers() {
        return skillOfferRepository.findAll();
    }
    
    public SkillOffer updateOffer(Long id, SkillOffer offerDetails) {
        SkillOffer offer = getOfferById(id);
        if (offerDetails.getExperienceLevel() != null) {
            offer.setExperienceLevel(offerDetails.getExperienceLevel());
        }
        if (offerDetails.getSkill() != null) {
            offer.setSkill(offerDetails.getSkill());
        }
        if (offerDetails.getUser() != null) {
            offer.setUser(offerDetails.getUser());
        }
        return skillOfferRepository.save(offer);
    }
    
    public void deleteOffer(Long id) {
        SkillOffer offer = getOfferById(id);
        skillOfferRepository.delete(offer);
    }
    
    public void softDeleteOffer(Long id) {
        SkillOffer offer = getOfferById(id);
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }
    
    public List<SkillOffer> getActiveOffers() {
        return skillOfferRepository.findAll().stream()
            .filter(SkillOffer::isActive)
            .toList();
    }
}