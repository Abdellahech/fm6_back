package com.example.FM6.controller;

import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enfant")
public class EnfantController {

    private final EnfantRepository enfantRepository;

    public EnfantController(EnfantRepository enfantRepository) {
        this.enfantRepository = enfantRepository;
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<Enfant> getEnfantProfile(@PathVariable Long id) {
        Enfant enfant = enfantRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(enfant);
    }
}
