package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill updateSkill(Long id, Skill skillDetails);

    List<Skill> getAllSkills();

    Skill getSkillById(Long id);

    void deleteSkill(Long id);
}
