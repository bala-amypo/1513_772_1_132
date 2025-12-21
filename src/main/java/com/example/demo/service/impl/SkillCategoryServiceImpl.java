package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repo;

    public SkillCategoryServiceImpl(SkillCategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        if (category.getName().length() < 5) {
            throw new BadRequestException("Skill name must be at least 5 characters");
        }
        return repo.save(category);
    }

    @Override
    public SkillCategory getCategory(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return repo.findAll();
    }
}
