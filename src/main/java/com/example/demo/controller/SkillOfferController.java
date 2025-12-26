package com.example.demo.controller;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class SkillOfferController {
    
    @Autowired
    private SkillOfferService skillOfferService;
    
    @PostMapping
    public ResponseEntity<SkillOffer> create(@RequestBody SkillOffer offer) {
        SkillOffer createdOffer = skillOfferService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillOffer> get(@PathVariable Long id) {
        SkillOffer offer = skillOfferService.getOfferById(id);
        return ResponseEntity.ok(offer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SkillOffer> update(@PathVariable Long id, @RequestBody SkillOffer offerDetails) {
        SkillOffer updatedOffer = skillOfferService.updateOffer(id, offerDetails);
        return ResponseEntity.ok(updatedOffer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        skillOfferService.deactivateOffer(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<SkillOffer>> list() {
        List<SkillOffer> offers = skillOfferService.getAllOffers();
        return ResponseEntity.ok(offers);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillOffer>> getByUser(@PathVariable Long userId) {
        List<SkillOffer> offers = skillOfferService.getOffersByUser(userId);
        return ResponseEntity.ok(offers);
    }
}