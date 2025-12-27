package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "match_records")
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "User A is required")
    @ManyToOne
    @JoinColumn(name = "user_a_id")
    private UserProfile userA;
    
    @NotNull(message = "User B is required")
    @ManyToOne
    @JoinColumn(name = "user_b_id")
    private UserProfile userB;
    
    @NotNull(message = "Skill offered by User A is required")
    @ManyToOne
    @JoinColumn(name = "skill_offered_by_a_id")
    private Skill skillOfferedByA;
    
    @NotNull(message = "Skill offered by User B is required")
    @ManyToOne
    @JoinColumn(name = "skill_offered_by_b_id")
    private Skill skillOfferedByB;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|APPROVED|REJECTED|COMPLETED|CANCELLED)$", 
             message = "Status must be PENDING, APPROVED, REJECTED, COMPLETED, or CANCELLED")
    private String status = "PENDING";
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUserA() { return userA; }
    public void setUserA(UserProfile userA) { this.userA = userA; }
    
    public UserProfile getUserB() { return userB; }
    public void setUserB(UserProfile userB) { this.userB = userB; }
    
    public Skill getSkillOfferedByA() { return skillOfferedByA; }
    public void setSkillOfferedByA(Skill skillOfferedByA) { this.skillOfferedByA = skillOfferedByA; }
    
    public Skill getSkillOfferedByB() { return skillOfferedByB; }
    public void setSkillOfferedByB(Skill skillOfferedByB) { this.skillOfferedByB = skillOfferedByB; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}