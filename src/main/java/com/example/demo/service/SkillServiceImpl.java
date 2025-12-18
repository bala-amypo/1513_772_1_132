package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(Skill skill) {
        skill.setActive(true);
        skill.setCreatedat(LocalDateTime.now());
        skill.setUpdatedat(LocalDateTime.now());
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isPresent()) {
            Skill existingSkill = optionalSkill.get();
            existingSkill.setUsername(skill.getUsername());
            existingSkill.setEmail(skill.getEmail());
            existingSkill.setBio(skill.getBio());
            existingSkill.setUpdatedat(LocalDateTime.now());
            return skillRepository.save(existingSkill);
        }
        return null; 
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null); // return null if not found
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill deactivateSkill(Long id) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isPresent()) {
            Skill skill = optionalSkill.get();
            skill.setActive(false);
            skill.setUpdatedat(LocalDateTime.now());
            return skillRepository.save(skill);
        }
        return null; 
    }
}
