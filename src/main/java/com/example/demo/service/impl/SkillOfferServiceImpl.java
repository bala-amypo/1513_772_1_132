package com.example.demo.service.impl;

import com.example.demo.model.SkillOffer;
import com.example.demo.repository.SkillOfferRepository;
import com.example.demo.service.SkillOfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        return skillOfferRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill offer not found " 
                ));
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
        if (offerDetails.getExperienceLevel() != null) {
            offer.setExperienceLevel(offerDetails.getExperienceLevel());
        }
        return skillOfferRepository.save(offer);
    }

    public void deactivateOffer(Long id) {
        SkillOffer offer = getOfferById(id);
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }
}
