package com.example.demo.controller;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skill-offers")
public class SkillOfferController {
    
    @Autowired
    private SkillOfferService skillOfferService;
    
    @PostMapping
    public ResponseEntity<SkillOffer> create(@RequestBody SkillOffer offer) {
        return ResponseEntity.ok(skillOfferService.createOffer(offer));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillOffer> get(@PathVariable Long id) {
        return ResponseEntity.ok(skillOfferService.getOfferById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<SkillOffer>> getAll() {
        return ResponseEntity.ok(((com.example.demo.service.impl.SkillOfferServiceImpl) skillOfferService).getAllOffers());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillOffer>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(skillOfferService.getOffersByUser(userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SkillOffer> update(@PathVariable Long id, @RequestBody SkillOffer offer) {
        SkillOffer updated = ((com.example.demo.service.impl.SkillOfferServiceImpl) skillOfferService).updateOffer(id, offer);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ((com.example.demo.service.impl.SkillOfferServiceImpl) skillOfferService).deleteOffer(id);
        return ResponseEntity.ok().build();
    }
}