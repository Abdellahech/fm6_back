package com.example.FM6.controller;

import com.example.FM6.dto.LoginRequest;
import com.example.FM6.dto.LoginResponse;
import com.example.FM6.entity.Adherent;
import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdherentRepository;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import com.example.FM6.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final AdherentRepository adherentRepository;
    private final AdjacentRepository adjacentRepository;
    private final EnfantRepository enfantRepository;

    public AuthController(AdherentRepository adherentRepository,
                          AdjacentRepository adjacentRepository,
                          EnfantRepository enfantRepository) {
        this.adherentRepository = adherentRepository;
        this.adjacentRepository = adjacentRepository;
        this.enfantRepository = enfantRepository;
    }

    @PostMapping("/adherent")
    public ResponseEntity<?> loginAdherent(@RequestBody LoginRequest request) {
        Optional<Adherent> user = adherentRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String token = JwtUtil.generateToken(user.get().getEmail(), "adherent");
            Long userId = user.get().getId(); // ✅ get adherent id
            return ResponseEntity.ok(new LoginResponse(token, userId));
        }
        return ResponseEntity.status(401).body("Invalid adherent credentials");
    }

    @PostMapping("/adjacent")
    public ResponseEntity<?> loginAdjacent(@RequestBody LoginRequest request) {
        Optional<Adjacent> user = adjacentRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String token = JwtUtil.generateToken(user.get().getEmail(), "adjacent");
            Long userId = user.get().getId(); // ✅ get adjacent id
            return ResponseEntity.ok(new LoginResponse(token, userId));
        }
        return ResponseEntity.status(401).body("Invalid adjacent credentials");
    }

    @PostMapping("/enfant")
    public ResponseEntity<?> loginEnfant(@RequestBody LoginRequest request) {
        Optional<Enfant> user = enfantRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String token = JwtUtil.generateToken(user.get().getEmail(), "enfant");
            Long userId = user.get().getId(); // ✅ get enfant id
            return ResponseEntity.ok(new LoginResponse(token, userId));
        }
        return ResponseEntity.status(401).body("Invalid enfant credentials");
    }
}
