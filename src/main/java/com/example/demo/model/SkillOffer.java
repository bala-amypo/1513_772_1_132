package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "skill_offers")
public class SkillOffer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private AppUser user;
    
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    @NotNull(message = "Skill is required")
    private Skill skill;
    
    @NotBlank(message = "Experience level is required")
    private String experienceLevel;
    
    private Boolean active;
    
    // Constructors
    public SkillOffer() {}
    
    public SkillOffer(AppUser user, Skill skill, String experienceLevel) {
        this.user = user;
        this.skill = skill;
        this.experienceLevel = experienceLevel;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
    
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    
    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}