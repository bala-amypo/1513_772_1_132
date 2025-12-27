// package com.example.demo.service.impl;

// import com.example.demo.model.Skill;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.service.SkillService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class SkillServiceImpl implements SkillService {
    
//     @Autowired
//     private SkillRepository skillRepository;
    
//     @Override
//     public Skill createSkill(Skill skill) {
//         skill.setActive(true);
//         return skillRepository.save(skill);
//     }
    
//     @Override
//     public Skill getSkillById(Long id) {
//         Optional<Skill> skill = skillRepository.findById(id);
//         if (skill.isPresent()) {
//             return skill.get();
//         }
//         throw new RuntimeException("Skill not found");
//     }
    
//     @Override
//     public Skill updateSkill(Long id, Skill skillDetails) {
//         Skill skill = new Skill();
//         skill.setId(id);
//         skill.setName(skillDetails.getName());
//         skill.setCategory(skillDetails.getCategory());
//         return skill;
//     }
    
    
    
//     @Override
//     public List<Skill> getAllSkills() {
//         return skillRepository.findAll();
//     }
// }

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
        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = getSkillById(id);
        if (skillDetails.getName() != null) {
            skill.setName(skillDetails.getName());
        }
        if (skillDetails.getCategory() != null) {
            skill.setCategory(skillDetails.getCategory());
        }
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}
