package com.example.estatechecker.repository;

import com.example.estatechecker.model.entity.BoligaEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoligaEstateRepository extends JpaRepository<BoligaEstate, UUID> {
}
