package com.example.FM6.dto;

import com.example.FM6.entity.Adjacent;
import com.example.FM6.entity.Enfant;
import java.util.List;

public class AdherentDetailsDTO {

    private Long id;
    private String name;
    private String email;
    private List<Adjacent> adjacents;
    private List<Enfant> enfants;

    public AdherentDetailsDTO(Long id, String name, String email, List<Adjacent> adjacents, List<Enfant> enfants) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adjacents = adjacents;
        this.enfants = enfants;
    }

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Adjacent> getAdjacents() { return adjacents; }
    public void setAdjacents(List<Adjacent> adjacents) { this.adjacents = adjacents; }

    public List<Enfant> getEnfants() { return enfants; }
    public void setEnfants(List<Enfant> enfants) { this.enfants = enfants; }
}
