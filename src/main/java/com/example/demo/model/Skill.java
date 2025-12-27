package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Skill name is required")
    @Size(min = 2, max = 100, message = "Skill name must be between 2 and 100 characters")
    @Column(unique = true)
    private String name;
    
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;
    
    private boolean active = true;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}