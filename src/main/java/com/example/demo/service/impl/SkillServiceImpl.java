package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.OperationException;
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
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new OperationException("Skill name is required");
        }
        
        if (skillRepository.findByName(skill.getName()) != null) {
            throw new OperationException("Skill with name '" + skill.getName() + "' already exists");
        }
        
        skill.setActive(true);
        return skillRepository.save(skill);
    }
    
    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        }
        throw new ResourceNotFoundException("Skill not found with id: " + id);
    }
    
    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
            
        if (!skill.isActive()) {
            throw new OperationException("Cannot update deactivated skill with ID " + id);
        }
        
        if (skillDetails.getName() != null && !skillDetails.getName().trim().isEmpty()) {
            Skill existingSkill = skillRepository.findByName(skillDetails.getName());
            if (existingSkill != null && !existingSkill.getId().equals(id)) {
                throw new OperationException("Skill with name '" + skillDetails.getName() + "' already exists");
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
        
        if (!skill.isActive()) {
            throw new OperationException("Cannot delete deactivated skill. Deactivate first.");
        }
        
        skillRepository.delete(skill);
    }
    
    @Override
    public void deactivateSkill(Long id) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
            
        if (!skill.isActive()) {
            throw new OperationException("Skill with ID " + id + " is already deactivated");
        }
        
        skill.setActive(false);
        skillRepository.save(skill);
    }
    
    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}