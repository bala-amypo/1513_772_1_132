package com.example.demo.service;

import com.example.demo.model.Skill;
import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);
    Skill getSkillById(Long id);
    Skill updateSkill(Long id, Skill skill);
    List<Skill> getAllSkills();
}