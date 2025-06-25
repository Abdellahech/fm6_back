package com.example.FM6.repository;

import com.example.FM6.entity.Adjacent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdjacentRepository extends JpaRepository<Adjacent, Long> {
    Optional<Adjacent> findByEmail(String email);
    List<Adjacent> findByAdherentId(Long adherentId);
}
