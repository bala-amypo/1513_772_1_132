package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String bio;
    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdat;
    @CreationTimestamp
    private LocalDateTime updatedat;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public LocalDateTime getCreatedat() {
        return createdat;
    }
    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }
    public LocalDateTime getUpdatedat() {
        return updatedat;
    }
    public void setUpdatedat(LocalDateTime updatedat) {
        this.updatedat = updatedat;
    }
    public Skill(Long id, String username, String email, String bio, Boolean active, LocalDateTime createdat,
            LocalDateTime updatedat) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.active = active;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }
    public Skill() {
    }
    

}
