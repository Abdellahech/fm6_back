package com.example.FM6.repository;

import com.example.FM6.entity.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Long> {
    Optional<Enfant> findByEmail(String email);
    List<Enfant> findByAdherentId(Long adherentId);
    Optional<Enfant> findByIdAndAdherentId(Long id, Long adherentId); // ✅ added method
}
