package com.example.FM6.controller;

import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/enfant")
public class EnfantController {

    private final EnfantRepository enfantRepository;

    public EnfantController(EnfantRepository enfantRepository) {
        this.enfantRepository = enfantRepository;
    }

    /**
     * ✅ GET endpoint to fetch an enfant profile by id.
     */
    @GetMapping("/{id}/profile")
    public ResponseEntity<Enfant> getEnfantProfile(@PathVariable Long id) {
        Enfant enfant = enfantRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(enfant);
    }

    /**
     * ✅ PUT endpoint to update an enfant's details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable Long id, @RequestBody Enfant updated) {
        Optional<Enfant> enfantOpt = enfantRepository.findById(id);
        if (enfantOpt.isPresent()) {
            Enfant enfant = enfantOpt.get();
            enfant.setName(updated.getName());
            enfant.setEmail(updated.getEmail());
            // Add other fields as needed
            enfantRepository.save(enfant);
            return ResponseEntity.ok(enfant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
