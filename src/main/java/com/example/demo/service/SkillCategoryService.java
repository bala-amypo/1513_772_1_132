package com.example.demo.service;

import com.example.demo.model.SkillCategory;

import java.util.List;

public interface SkillCategoryService {
    SkillCategory createCategory(SkillCategory category);
    SkillCategory getCategory(Long id);
    List<SkillCategory> getAllCategories();
}
