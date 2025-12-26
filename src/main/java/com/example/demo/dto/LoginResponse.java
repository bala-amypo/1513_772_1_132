package com.example.demo.dto;

public class LoginResponse {
    private String email;
    private String token;
    private String role;  // Add this field
    private Long userId;  // Add this field
    
    // Constructors
    public LoginResponse() {}
    
    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
    
    public LoginResponse(String email, String token, String role, Long userId) {
        this.email = email;
        this.token = token;
        this.role = role;
        this.userId = userId;
    }
    
    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    
}