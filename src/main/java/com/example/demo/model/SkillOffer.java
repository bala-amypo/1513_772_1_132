package com.example.demo.model;

public class SkillOffer {
    private Long id;
    private UserProfile user;
    private Skill skill;
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
