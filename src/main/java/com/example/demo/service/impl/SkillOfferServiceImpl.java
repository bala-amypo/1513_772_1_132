package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.OperationException;
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
        if (offer.getUser() == null) {
            throw new OperationException("User is required for skill offer");
        }
        if (offer.getSkill() == null) {
            throw new OperationException("Skill is required for skill offer");
        }
        if (offer.getExperienceLevel() == null || offer.getExperienceLevel().trim().isEmpty()) {
            throw new OperationException("Experience level is required");
        }
        
        offer.setActive(true);
        return skillOfferRepository.save(offer);
    }
    
    @Override
    public SkillOffer getOfferById(Long id) {
        Optional<SkillOffer> offer = skillOfferRepository.findById(id);
        if (offer.isPresent()) {
            return offer.get();
        }
        throw new ResourceNotFoundException("SkillOffer not found with id: " + id);
    }
    
    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return skillOfferRepository.findByUserId(userId);
    }
    
    @Override
    public SkillOffer updateOffer(Long id, SkillOffer offerDetails) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
            
        if (!offer.isActive()) {
            throw new OperationException("Cannot update deactivated skill offer with ID " + id);
        }
        
        if (offerDetails.getExperienceLevel() != null) {
            offer.setExperienceLevel(offerDetails.getExperienceLevel());
        }
        return skillOfferRepository.save(offer);
    }
    
    @Override
    public void deleteOffer(Long id) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
        
        if (!offer.isActive()) {
            throw new OperationException("Cannot delete deactivated offer. Deactivate first.");
        }
        
        skillOfferRepository.delete(offer);
    }
    
    @Override
    public void deactivateOffer(Long id) {
        SkillOffer offer = skillOfferRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillOffer not found with id: " + id));
            
        if (!offer.isActive()) {
            throw new OperationException("SkillOffer with ID " + id + " is already deactivated");
        }
        
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }
    
    @Override
    public List<SkillOffer> getAllOffers() {
        return skillOfferRepository.findAll();
    }
}