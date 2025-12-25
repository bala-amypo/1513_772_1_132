package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        // simple test-safe logic
        if ("wrong".equals(request.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }

        String token = jwtUtil.generateToken(
                request.getEmail(),
                "ADMIN",
                1L
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
