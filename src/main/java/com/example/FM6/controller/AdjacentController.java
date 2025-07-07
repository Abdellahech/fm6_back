package com.example.FM6.controller;

import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adjacent")
public class AdjacentController {

    private final AdjacentRepository adjacentRepository;
    private final EnfantRepository enfantRepository;

    public AdjacentController(AdjacentRepository adjacentRepository, EnfantRepository enfantRepository) {
        this.adjacentRepository = adjacentRepository;
        this.enfantRepository = enfantRepository;
    }

    /**
     * ✅ GET endpoint to fetch an adjacent profile with their enfants.
     * This assumes each adjacent is linked to an adherent, and enfants are linked to that adherent.
     */
    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getAdjacentProfile(@PathVariable Long id) {
        Adjacent adjacent = adjacentRepository.findById(id).orElseThrow();
        List<Enfant> enfants = enfantRepository.findByAdherentId(adjacent.getAdherent().getId());
        return ResponseEntity.ok(new AdjacentProfileResponse(adjacent, enfants));
    }

    /**
     * ✅ Record to encapsulate adjacent + enfants in response.
     */
    public record AdjacentProfileResponse(Adjacent adjacent, List<Enfant> enfants) {}

    /**
     * ✅ PUT endpoint to update adjacent details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Adjacent> updateAdjacent(@PathVariable Long id, @RequestBody Adjacent updated) {
        Optional<Adjacent> adjacentOpt = adjacentRepository.findById(id);
        if (adjacentOpt.isPresent()) {
            Adjacent adjacent = adjacentOpt.get();
            adjacent.setName(updated.getName());
            adjacent.setEmail(updated.getEmail());
            // Add other fields as needed
            adjacentRepository.save(adjacent);
            return ResponseEntity.ok(adjacent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
