package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    @Column(nullable = false)
    private double rating = 0.0;

    private String location;

    @NotBlank
    @Pattern(regexp = "ADMIN|USER", message = "Role must be ADMIN or USER")
    @Column(nullable = false)
    private String role = "USER";

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    public User() {}

    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.setPassword(password);
        this.role = role != null ? role : "USER";
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equals("ADMIN") || role.equals("USER")) {
            this.role = role;
        }
    }

    public String getPassword() {
        return password;
    }
}
