package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private AppUser appUser;
    
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean active;
    
    // Constructors
    public UserProfile() {}
    
    public UserProfile(String username, String email, AppUser appUser) {
        this.username = username;
        this.email = email;
        this.appUser = appUser;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public AppUser getAppUser() { return appUser; }
    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}