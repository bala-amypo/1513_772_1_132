package com.example.demo.service.impl;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillOfferServiceImpl implements SkillOfferService {
    @Override
    public SkillOffer createOffer(SkillOffer offer) {
        return offer;
    }
    
    @Override
    public SkillOffer getOfferById(Long id) {
        SkillOffer offer = new SkillOffer();
        offer.setId(id);
        return offer;
    }
    
    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        return new ArrayList<>();
    }
}