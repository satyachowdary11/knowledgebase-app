package com.com.kba.knowledgebase.controller;

import com.com.kba.knowledgebase.entity.User;
import com.com.kba.knowledgebase.repository.UserRepository;
import com.com.kba.knowledgebase.security.CustomUserDetailsService;
import com.com.kba.knowledgebase.security.JwtTokenUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.get("username"),
                loginRequest.get("password"))
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.get("username"));
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return Map.of("token", token);
    }
}
