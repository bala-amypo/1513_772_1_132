package com.example.demo.controller;

import com.example.demo.model.SkillOffer;
import com.example.demo.service.SkillOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skill-offers")
@Validated
public class SkillOfferController {
    
    @Autowired
    private SkillOfferService skillOfferService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SkillOffer> create(@Valid @RequestBody SkillOffer offer) {
        SkillOffer createdOffer = skillOfferService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SkillOffer> get(@PathVariable Long id) {
        SkillOffer offer = skillOfferService.getOfferById(id);
        return ResponseEntity.ok(offer);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SkillOffer> update(@PathVariable Long id, @Valid @RequestBody SkillOffer offer) {
        SkillOffer updatedOffer = skillOfferService.updateOffer(id, offer);
        return ResponseEntity.ok(updatedOffer);
    }
    
    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        skillOfferService.deactivateOffer(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Skill offer deactivated successfully");
        response.put("status", "SUCCESS");
        response.put("offerId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<SkillOffer>> getAll() {
        List<SkillOffer> offers = skillOfferService.getAllOffers();
        return ResponseEntity.ok(offers);
    }
    
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<SkillOffer>> getByUser(@PathVariable Long userId) {
        List<SkillOffer> offers = skillOfferService.getOffersByUser(userId);
        return ResponseEntity.ok(offers);
    }
}