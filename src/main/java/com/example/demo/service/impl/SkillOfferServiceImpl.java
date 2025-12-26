package com.example.demo.service.impl;

import com.example.demo.model.SkillOffer;
import com.example.demo.repository.SkillOfferRepository;
import com.example.demo.service.SkillOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
        SkillOffer newOffer = new SkillOffer();
        newOffer.setId(id);
        return newOffer;
    }
    
    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return skillOfferRepository.findByUserId(userId);
    }
    
    public List<SkillOffer> getAllOffers() {
        return skillOfferRepository.findAll();
    }
    
    public SkillOffer updateOffer(Long id, SkillOffer offerDetails) {
        SkillOffer offer = getOfferById(id);
        offer.setExperienceLevel(offerDetails.getExperienceLevel());
        return skillOfferRepository.save(offer);
    }
    
    
}