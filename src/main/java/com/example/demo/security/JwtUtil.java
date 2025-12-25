package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing tokens (keep secure in production)
    private static final String SECRET_KEY = "MySuperSecureKeyForSkillBarterPlatform1234567890"; // min 256-bit for HS256

    // Token validity: 24 hours
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generate JWT token with email, role, and userId
     */
    public String generateToken(String email, String role, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Extract email (subject) from token
     */
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Extract role from token
     */
    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    /**
     * Extract userId from token
     */
    public Long extractUserId(String token) {
        Object id = getClaims(token).get("userId");
        if (id instanceof Integer) return ((Integer) id).longValue();
        else return (Long) id;
    }

    /**
     * Helper to parse claims from token
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
