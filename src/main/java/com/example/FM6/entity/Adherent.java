package com.example.FM6.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL)
    @JsonManagedReference("adherent-adjacents")
    private List<Adjacent> adjacents;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL)
    @JsonManagedReference("adherent-enfants")
    private List<Enfant> enfants;

    // Getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Adjacent> getAdjacents() { return adjacents; }

    public void setAdjacents(List<Adjacent> adjacents) { this.adjacents = adjacents; }

    public List<Enfant> getEnfants() { return enfants; }

    public void setEnfants(List<Enfant> enfants) { this.enfants = enfants; }
}
