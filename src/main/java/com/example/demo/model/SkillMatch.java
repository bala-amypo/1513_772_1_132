package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_matches")
public class SkillMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long offerId;
    private Long requestId;
    private String status;

    public SkillMatch() {}

    public SkillMatch(Long offerId, Long requestId) {
        this.offerId = offerId;
        this.requestId = requestId;
        this.status = "PENDING";
    }

    public SkillMatch(Long offerId, Long requestId, String status) {
        this.offerId = offerId;
        this.requestId = requestId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
