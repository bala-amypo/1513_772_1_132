package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill updateSkill(Long id, Skill skill); // throw "Skill not found"

    Skill getSkillById(Long id);

    List<Skill> getAllSkills();

    Skill deactivateSkill(Long id);
}
