package com.example.demo.service;

import com.example.demo.model.Skill;
import java.util.List;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill getSkillById(Long id);

    List<Skill> getAllSkills();

    Skill updateSkill(Long id, Skill skillDetails);
    
    void deactivateSkill(Long id);
}