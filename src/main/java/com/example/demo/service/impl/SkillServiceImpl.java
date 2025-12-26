package com.example.demo.service.impl;

import com.example.demo.exception.*;
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
        // Check if skill with same name exists
        Optional<Skill> existingSkill = skillRepository.findAll().stream()
            .filter(s -> skill.getName() != null && skill.getName().equalsIgnoreCase(s.getName()))
            .findFirst();
            
        if (existingSkill.isPresent()) {
            throw new ResourceAlreadyExistsException("Skill with name '" + skill.getName() + "' already exists");
        }
        
        // Validate required fields
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new ValidationException("Skill name is required");
        }
        
        skill.setActive(true);
        return skillRepository.save(skill);
    }
    
    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            if (!skill.get().isActive()) {
                throw new ResourceInactiveException("Skill with ID " + id + " is deactivated");
            }
            return skill.get();
        }
        throw new ResourceNotFoundException("Skill not found with id: " + id);
    }
    
    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
            
        if (!skill.isActive()) {
            throw new ResourceInactiveException("Cannot update deactivated skill with ID " + id);
        }
        
        // Check if name is being changed to an existing skill name
        if (skillDetails.getName() != null && !skillDetails.getName().equals(skill.getName())) {
            Optional<Skill> existingSkill = skillRepository.findAll().stream()
                .filter(s -> skillDetails.getName().equalsIgnoreCase(s.getName()))
                .findFirst();
                
            if (existingSkill.isPresent()) {
                throw new ResourceAlreadyExistsException("Skill with name '" + skillDetails.getName() + "' already exists");
            }
            skill.setName(skillDetails.getName());
        }
        
        if (skillDetails.getCategory() != null) {
            skill.setCategory(skillDetails.getCategory());
        }
        
        return skillRepository.save(skill);
    }
    
    @Override
    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        
        // Check if skill is being used in offers or requests
        // For simplicity, we'll just delete it
        skillRepository.delete(skill);
    }
    
    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    public List<Skill> getActiveSkills() {
        return skillRepository.findAll().stream()
            .filter(Skill::isActive)
            .toList();
    }
    
    public void deactivateSkill(Long id) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
            
        if (!skill.isActive()) {
            throw new ResourceInactiveException("Skill with ID " + id + " is already deactivated");
        }
        
        skill.setActive(false);
        skillRepository.save(skill);
    }
}