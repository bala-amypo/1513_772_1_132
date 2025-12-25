package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final List<Skill> skills = new ArrayList<>();

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        skill.setId(id);
        return skill;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skills;
    }
}
