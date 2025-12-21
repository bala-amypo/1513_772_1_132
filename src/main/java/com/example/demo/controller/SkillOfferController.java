package com.example.demo.controller;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class SkillOfferController {

    private final SkillOfferService offerService;

    public SkillOfferController(SkillOfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<SkillOffer> createOffer(@RequestBody SkillOffer offer) {
        return ResponseEntity.ok(offerService.createOffer(offer));
    }

    @GetMapping
    public ResponseEntity<List<SkillOffer>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillOffer> getOffer(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillOffer>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(offerService.getOffersByUser(userId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SkillOffer>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(offerService.getOffersByCategory(categoryId));
    }

    @GetMapping("/available")
    public ResponseEntity<List<SkillOffer>> getAvailableOffers() {
        return ResponseEntity.ok(offerService.getAvailableOffers());
    }
}
