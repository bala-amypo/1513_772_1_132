package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "skill_requests")
public class SkillRequest {
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
    
    @NotBlank(message = "Urgency level is required")
    @Pattern(regexp = "^(LOW|MEDIUM|HIGH|CRITICAL)$", 
             message = "Urgency level must be LOW, MEDIUM, HIGH, or CRITICAL")
    private String urgencyLevel;
    
    private boolean active = true;
    
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