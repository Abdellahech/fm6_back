package com.example.FM6.service;

import com.example.FM6.dto.RegistrationRequest;
import com.example.FM6.entity.Adherent;
import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import com.example.FM6.repository.AdherentRepository;
import com.example.FM6.repository.AdjacentRepository;
import com.example.FM6.repository.EnfantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private AdjacentRepository adjacentRepository;

    @Autowired
    private EnfantRepository enfantRepository;

    public Adherent registerFamily(RegistrationRequest request) {
        // Create and save Adherent
        Adherent adherent = new Adherent();
        adherent.setName(request.adherentName);
        adherent.setEmail(request.adherentEmail);
        adherent.setPassword(request.adherentPassword);
        Adherent savedAdherent = adherentRepository.save(adherent);

        // Create and save Adjacent list
        List<Adjacent> adjacents = request.adjacents.stream().map(a -> {
            Adjacent adjacent = new Adjacent();
            adjacent.setName(a.name);
            adjacent.setEmail(a.email);
            adjacent.setPassword(a.password);
            adjacent.setAdherent(savedAdherent);
            return adjacent;
        }).collect(Collectors.toList());
        adjacentRepository.saveAll(adjacents);

        // Create and save Enfant list
        List<Enfant> enfants = request.enfants.stream().map(e -> {
            Enfant enfant = new Enfant();
            enfant.setName(e.name);
            enfant.setEmail(e.email);
            enfant.setPassword(e.password);
            enfant.setAdherent(savedAdherent);
            return enfant;
        }).collect(Collectors.toList());
        enfantRepository.saveAll(enfants);

        // Attach children and adjacents to Adherent
        savedAdherent.setAdjacents(adjacents);
        savedAdherent.setEnfants(enfants);

        return savedAdherent;
    }
}
