package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "skill_requests")
public class SkillRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfile user;
    
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    private String urgencyLevel;
    private boolean active = true;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUser() { return user; }
    public void setUser(UserProfile user) { this.user = user; }
    
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}