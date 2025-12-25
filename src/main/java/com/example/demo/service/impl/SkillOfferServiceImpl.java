package com.example.demo.service.impl;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillOfferServiceImpl implements SkillOfferService {

    private final List<SkillOffer> offers = new ArrayList<>();

    @Override
    public SkillOffer createOffer(SkillOffer offer) {
        offers.add(offer);
        return offer;
    }

    @Override
    public SkillOffer getOfferById(Long id) {
        return offers.stream().filter(o -> o.getId() != null && o.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    @Override
    public List<SkillOffer> getOffersByUser(Long userId) {
        List<SkillOffer> result = new ArrayList<>();
        for (SkillOffer o : offers) {
            if (o.getUser() != null && o.getUser().getId().equals(userId)) result.add(o);
        }
        return result;
    }
}
