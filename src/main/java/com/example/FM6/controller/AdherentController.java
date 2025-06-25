package com.example.FM6.controller;

import com.example.FM6.entity.Adherent;
import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdherentRepository;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adherent")
public class AdherentController {

    private final AdherentRepository adherentRepository;
    private final AdjacentRepository adjacentRepository;
    private final EnfantRepository enfantRepository;

    public AdherentController(AdherentRepository adherentRepository, AdjacentRepository adjacentRepository, EnfantRepository enfantRepository) {
        this.adherentRepository = adherentRepository;
        this.adjacentRepository = adjacentRepository;
        this.enfantRepository = enfantRepository;
    }

    // ✅ Get Adherent by Email (for login/lookup)
    @GetMapping("/email/{email}")
    public ResponseEntity<Adherent> getAdherentByEmail(@PathVariable String email) {
        Adherent adherent = adherentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Adherent not found with email: " + email));
        return ResponseEntity.ok(adherent);
    }

    // Fetch adherent + enfants + adjacents
    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getAdherentProfile(@PathVariable Long id) {
        Adherent adherent = adherentRepository.findById(id).orElseThrow();
        List<Adjacent> adjacents = adjacentRepository.findByAdherentId(id);
        List<Enfant> enfants = enfantRepository.findByAdherentId(id);

        return ResponseEntity.ok(new AdherentProfileResponse(adherent, adjacents, enfants));
    }

    // CRUD for Adherent
    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherent(@PathVariable Long id) {
        return ResponseEntity.ok(adherentRepository.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Long id, @RequestBody Adherent updated) {
        Adherent adherent = adherentRepository.findById(id).orElseThrow();
        adherent.setName(updated.getName());
        adherent.setEmail(updated.getEmail());
        adherent.setPassword(updated.getPassword());
        return ResponseEntity.ok(adherentRepository.save(adherent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdherent(@PathVariable Long id) {
        adherentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // CRUD for Adjacent
    @PostMapping("/{adherentId}/adjacent")
    public ResponseEntity<Adjacent> addAdjacent(@PathVariable Long adherentId, @RequestBody Adjacent adjacent) {
        Adherent adherent = adherentRepository.findById(adherentId)
                .orElseThrow(() -> new RuntimeException("Adherent not found"));
        adjacent.setAdherent(adherent);
        return ResponseEntity.ok(adjacentRepository.save(adjacent));
    }

    @PutMapping("/adjacent/{id}")
    public ResponseEntity<Adjacent> updateAdjacent(@PathVariable Long id, @RequestBody Adjacent updated) {
        Adjacent adjacent = adjacentRepository.findById(id).orElseThrow();
        adjacent.setName(updated.getName());
        adjacent.setEmail(updated.getEmail());
        adjacent.setPassword(updated.getPassword());
        return ResponseEntity.ok(adjacentRepository.save(adjacent));
    }

    @DeleteMapping("/adjacent/{id}")
    public ResponseEntity<?> deleteAdjacent(@PathVariable Long id) {
        adjacentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // CRUD for Enfant
    @PostMapping("/{adherentId}/enfant")
    public ResponseEntity<Enfant> addEnfant(@PathVariable Long adherentId, @RequestBody Enfant enfant) {
        Adherent adherent = adherentRepository.findById(adherentId)
                .orElseThrow(() -> new RuntimeException("Adherent not found"));
        enfant.setAdherent(adherent);
        return ResponseEntity.ok(enfantRepository.save(enfant));
    }

    @PutMapping("/enfant/{id}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable Long id, @RequestBody Enfant updated) {
        Enfant enfant = enfantRepository.findById(id).orElseThrow();
        enfant.setName(updated.getName());
        enfant.setEmail(updated.getEmail());
        enfant.setPassword(updated.getPassword());
        return ResponseEntity.ok(enfantRepository.save(enfant));
    }

    @DeleteMapping("/enfant/{id}")
    public ResponseEntity<?> deleteEnfant(@PathVariable Long id) {
        enfantRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // DTO for profile response
    public record AdherentProfileResponse(Adherent adherent, List<Adjacent> adjacents, List<Enfant> enfants) {}
}
