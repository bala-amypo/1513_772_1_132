package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String category;
    private boolean active = true;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
