package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.SkillOffer;
import com.example.demo.repository.SkillOfferRepository;

@Service
public class SkillOfferServiceImpl implements SkillOfferService {

    private final SkillOfferRepository skillOfferRepository;

    public SkillOfferServiceImpl(SkillOfferRepository skillOfferRepository) {
        this.skillOfferRepository = skillOfferRepository;
    }

    @Override
    public SkillOffer createOffer(SkillOffer offer) {
        validateExperienceLevel(offer.getExperienceLevel());
        offer.setActive(true);
        return skillOfferRepository.save(offer);
    }

    @Override
    public SkillOffer updateOffer(Long id, SkillOffer offer) {
        SkillOffer existing = skillOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        validateExperienceLevel(offer.getExperienceLevel());

        existing.setUserId(offer.getUserId());
        existing.setSkillId(offer.getSkillId());
        existing.setExperienceLevel(offer.getExperienceLevel());
        existing.setAvailableHoursPerWeek(offer.getAvailableHoursPerWeek());
        existing.setActive(offer.getActive());

        return skillOfferRepository.save(existing);
    }

    @Override
    public SkillOffer getOfferById(Long id) {
        return skillOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return skillOfferRepository.findByUserId(userId);
    }

    @Override
    public void deactivateOffer(Long id) {
        SkillOffer offer = skillOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        offer.setActive(false);
        skillOfferRepository.save(offer);
    }

    private void validateExperienceLevel(String level) {
        if (!"Beginner".equalsIgnoreCase(level) &&
            !"Intermediate".equalsIgnoreCase(level) &&
            !"Expert".equalsIgnoreCase(level)) {
            throw new RuntimeException("Invalid experience level");
        }
    }
}
