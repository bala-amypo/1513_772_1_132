package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_offers")
public class SkillOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long categoryId;
    private String description;

    private boolean available = true;

    public SkillOffer() {}

    public SkillOffer(Long userId, Long categoryId, String description) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.description = description;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
