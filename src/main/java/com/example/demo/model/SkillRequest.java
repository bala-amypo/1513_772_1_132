// SkillRequest.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SkillRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long skillId;
    private String urgencyLevel; // Low, Medium, High
    private Boolean active = true;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getSkillId() { return skillId; }
    public void setSkillId(Long skillId) { this.skillId = skillId; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
