package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userAId;
    private Long userBId;
    private Long skillOfferedByAId;
    private Long skillOfferedByBId;
    private LocalDateTime matchedAt = LocalDateTime.now();
    private String status = "PENDING";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserAId() { return userAId; }
    public void setUserAId(Long userAId) { this.userAId = userAId; }
    public Long getUserBId() { return userBId; }
    public void setUserBId(Long userBId) { this.userBId = userBId; }
    public Long getSkillOfferedByAId() { return skillOfferedByAId; }
    public void setSkillOfferedByAId(Long skillOfferedByAId) { this.skillOfferedByAId = skillOfferedByAId; }
    public Long getSkillOfferedByBId() { return skillOfferedByBId; }
    public void setSkillOfferedByBId(Long skillOfferedByBId) { this.skillOfferedByBId = skillOfferedByBId; }
    public LocalDateTime getMatchedAt() { return matchedAt; }
    public void setMatchedAt(LocalDateTime matchedAt) { this.matchedAt = matchedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


