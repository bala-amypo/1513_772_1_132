package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id,
                                        @RequestBody Skill skill) {
        return ResponseEntity.ok(service.updateSkill(id, skill));
    }

    @GetMapping
    public ResponseEntity<List<Skill>> list() {
        return ResponseEntity.ok(service.getAllSkills());
    }
}
