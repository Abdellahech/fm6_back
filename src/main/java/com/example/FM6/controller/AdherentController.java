package com.example.FM6.controller;

import com.example.FM6.dto.AdherentDetailsDTO;
import com.example.FM6.entity.Adherent;
import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdherentRepository;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adherents")
public class AdherentController {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private AdjacentRepository adjacentRepository;

    @Autowired
    private EnfantRepository enfantRepository;

    @GetMapping("/{id}/details")
    public ResponseEntity<AdherentDetailsDTO> getAdherentDetails(@PathVariable Long id) {
        Optional<Adherent> adherentOpt = adherentRepository.findById(id);
        if (adherentOpt.isPresent()) {
            Adherent adherent = adherentOpt.get();
            List<Adjacent> adjacents = adjacentRepository.findByAdherentId(id);
            List<Enfant> enfants = enfantRepository.findByAdherentId(id);

            AdherentDetailsDTO dto = new AdherentDetailsDTO(
                adherent.getId(),
                adherent.getName(),
                adherent.getEmail(),
                adjacents,
                enfants
            );

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Long id, @RequestBody Adherent updated) {
        Optional<Adherent> adherentOpt = adherentRepository.findById(id);
        if (adherentOpt.isPresent()) {
            Adherent adherent = adherentOpt.get();
            adherent.setName(updated.getName());
            adherent.setEmail(updated.getEmail());
            // add other fields to update as needed
            adherentRepository.save(adherent);
            return ResponseEntity.ok(adherent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{adherentId}/adjacents/{adjacentId}")
    public ResponseEntity<Adjacent> updateAdjacent(@PathVariable Long adherentId,
                                                   @PathVariable Long adjacentId,
                                                   @RequestBody Adjacent updated) {
        Optional<Adjacent> adjacentOpt = adjacentRepository.findByIdAndAdherentId(adjacentId, adherentId);
        if (adjacentOpt.isPresent()) {
            Adjacent adjacent = adjacentOpt.get();
            adjacent.setName(updated.getName());
            adjacent.setEmail(updated.getEmail());
            // add other fields as needed
            adjacentRepository.save(adjacent);
            return ResponseEntity.ok(adjacent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{adherentId}/enfants/{enfantId}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable Long adherentId,
                                               @PathVariable Long enfantId,
                                               @RequestBody Enfant updated) {
        Optional<Enfant> enfantOpt = enfantRepository.findByIdAndAdherentId(enfantId, adherentId);
        if (enfantOpt.isPresent()) {
            Enfant enfant = enfantOpt.get();
            enfant.setName(updated.getName());
            enfant.setEmail(updated.getEmail());
            // add other fields as needed
            enfantRepository.save(enfant);
            return ResponseEntity.ok(enfant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
