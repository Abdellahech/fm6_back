package com.example.FM6.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Enfant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    @JsonBackReference("adherent-enfants")
    private Adherent adherent;

    // Getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Adherent getAdherent() { return adherent; }

    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
}
