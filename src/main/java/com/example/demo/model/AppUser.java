package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;

    private String role;
    private LocalDateTime createdAt;

    // getters & setters
    public Long getId() { return id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
