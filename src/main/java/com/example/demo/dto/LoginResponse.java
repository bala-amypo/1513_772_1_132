package com.example.demo.dto;

public class LoginResponse {
    private String email;
    private String token;
    
    // Constructors
    public LoginResponse() {}
    
    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
    
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}