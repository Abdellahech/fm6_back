package com.example.FM6.controller;

import com.example.FM6.entity.User;
import com.example.FM6.repository.UserRepository;
import com.example.FM6.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existing = userRepository.findByUsername(user.getUsername());

        if (existing.isPresent() && existing.get().getPasswordHash().equals(user.getPasswordHash())) {
            String token = JwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
