package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;

@RestController
@RequestMapping("/api/skill-offers")
public class SkillOfferController {

    private final SkillOfferService skillOfferService;

    public SkillOfferController(SkillOfferService skillOfferService) {
        this.skillOfferService = skillOfferService;
    }

    @PostMapping("/")
    public SkillOffer createOffer(@RequestBody SkillOffer offer) {
        return skillOfferService.createOffer(offer);
    }

    @PutMapping("/{id}")
    public SkillOffer updateOffer(@PathVariable Long id, @RequestBody SkillOffer offer) {
        return skillOfferService.updateOffer(id, offer);
    }

    @GetMapping("/{id}")
    public SkillOffer getOfferById(@PathVariable Long id) {
        return skillOfferService.getOfferById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SkillOffer> getOffersByUser(@PathVariable Long userId) {
        return skillOfferService.getOffersByUser(userId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateOffer(@PathVariable Long id) {
        skillOfferService.deactivateOffer(id);
    }
}
