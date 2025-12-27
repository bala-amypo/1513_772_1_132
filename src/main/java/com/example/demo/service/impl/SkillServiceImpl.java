package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty");
        }
        if (skill.getCategory() == null || skill.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        
        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill ID");
        }
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill ID");
        }
        
        Skill skill = getSkillById(id);
        
        if (skillDetails.getName() != null) {
            if (skillDetails.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Skill name cannot be empty");
            }
            skill.setName(skillDetails.getName());
        }
        if (skillDetails.getCategory() != null) {
            if (skillDetails.getCategory().trim().isEmpty()) {
                throw new IllegalArgumentException("Category cannot be empty");
            }
            skill.setCategory(skillDetails.getCategory());
        }
        return skillRepository.save(skill);
    }
    
    @Override
    public void deactivateSkill(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill ID");
        }
        
        Skill skill = getSkillById(id);
        skill.setActive(false);
        skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}