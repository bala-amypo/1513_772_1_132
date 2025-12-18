package com.example.demo.service;

import java.util.List;
import com.example.demo.model.SkillOffer;

public interface SkillOfferService {
    SkillOffer createOffer(SkillOffer offer);
    SkillOffer updateOffer(Long id, SkillOffer offer);
    SkillOffer getOfferById(Long id);
    List<SkillOffer> getOffersByUser(Long userId);
    void deactivateOffer(Long id);
}
