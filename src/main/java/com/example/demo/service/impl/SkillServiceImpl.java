package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    @Override
    public Skill createSkill(Skill skill) {
        return skill;
    }
    
    @Override
    public Skill getSkillById(Long id) {
        Skill skill = new Skill();
        skill.setId(id);
        return skill;
    }
    
    @Override
    public Skill updateSkill(Long id, Skill skill) {
        skill.setId(id);
        return skill;
    }
    
    @Override
    public void deleteSkill(Long id) {
        // Delete implementation
    }
    
    @Override
    public List<Skill> getAllSkills() {
        return new ArrayList<>();
    }
}