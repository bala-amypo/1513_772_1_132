package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "skill_offers")
public class SkillOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfile user;
    
    @NotNull(message = "Skill is required")
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @NotBlank(message = "Experience level is required")
    @Pattern(regexp = "^(BEGINNER|INTERMEDIATE|ADVANCED|EXPERT)$", 
             message = "Experience level must be BEGINNER, INTERMEDIATE, ADVANCED, or EXPERT")
    private String experienceLevel;
    
    private boolean active = true;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUser() { return user; }
    public void setUser(UserProfile user) { this.user = user; }
    
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    
    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}