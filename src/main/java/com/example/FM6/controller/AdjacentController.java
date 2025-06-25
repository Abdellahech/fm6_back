package com.example.FM6.controller;

import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adjacent")
public class AdjacentController {

    private final AdjacentRepository adjacentRepository;
    private final EnfantRepository enfantRepository;

    public AdjacentController(AdjacentRepository adjacentRepository, EnfantRepository enfantRepository) {
        this.adjacentRepository = adjacentRepository;
        this.enfantRepository = enfantRepository;
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getAdjacentProfile(@PathVariable Long id) {
        Adjacent adjacent = adjacentRepository.findById(id).orElseThrow();
        List<Enfant> enfants = enfantRepository.findByAdherentId(adjacent.getAdherent().getId());
        return ResponseEntity.ok(new AdjacentProfileResponse(adjacent, enfants));
    }

    public record AdjacentProfileResponse(Adjacent adjacent, List<Enfant> enfants) {}
}
