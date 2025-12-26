package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    
    @Autowired
    private SkillService skillService;
    
    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Skill> get(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        return ResponseEntity.ok(skill);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill skillDetails) {
        Skill updatedSkill = skillService.updateSkill(id, skillDetails);
        return ResponseEntity.ok(updatedSkill);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        skillService.deactivateSkill(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Skill>> list() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }
}