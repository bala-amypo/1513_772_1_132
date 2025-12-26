package com.example.demo.service.impl;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Override
    public Skill createSkill(Skill skill) {
        skill.setActive(true);
        return skillRepository.save(skill);
    }
    
    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        }
        throw new RuntimeException("Skill not found with id: " + id);
    }
    
    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = getSkillById(id); // This will throw "Skill not found" if not found
        skill.setName(skillDetails.getName());
        skill.setCategory(skillDetails.getCategory());
        return skillRepository.save(skill);
    }
    
    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getSkillById(id); // This will throw "Skill not found" if not found
        skill.setActive(false);
        skillRepository.save(skill);
    }
    
    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}