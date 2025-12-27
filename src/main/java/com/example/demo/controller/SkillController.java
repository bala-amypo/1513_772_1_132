package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
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
@RequestMapping("/api/skills")
@Validated
public class SkillController {
    
    @Autowired
    private SkillService skillService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Skill> get(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        return ResponseEntity.ok(skill);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Skill> update(@PathVariable Long id, @Valid @RequestBody Skill skill) {
        Skill updatedSkill = skillService.updateSkill(id, skill);
        return ResponseEntity.ok(updatedSkill);
    }
    
    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        skillService.deactivateSkill(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Skill deactivated successfully");
        response.put("status", "SUCCESS");
        response.put("skillId", id.toString());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Skill>> list() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }
}