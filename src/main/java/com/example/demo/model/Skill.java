package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Skill name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    private Boolean active;
    
    @OneToMany(mappedBy = "skill")
    private List<SkillOffer> skillOffers;
    
    @OneToMany(mappedBy = "skill")
    private List<SkillRequest> skillRequests;
    
    // Constructors
    public Skill() {}
    
    public Skill(String name, String category) {
        this.name = name;
        this.category = category;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<SkillOffer> getSkillOffers() { return skillOffers; }
    public void setSkillOffers(List<SkillOffer> skillOffers) { this.skillOffers = skillOffers; }
    
    public List<SkillRequest> getSkillRequests() { return skillRequests; }
    public void setSkillRequests(List<SkillRequest> skillRequests) { this.skillRequests = skillRequests; }
}