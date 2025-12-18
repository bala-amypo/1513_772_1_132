package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // POST /api/skills
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    // PUT /api/skills/{id}
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id,
                             @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    // GET /api/skills/{id}
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    // GET /api/skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // PUT /api/skills/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public Skill deactivateSkill(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        if (skill != null) {
            skill.setActive(false);
            return skillService.updateSkill(id, skill);
        }
        return null;
    }
}
