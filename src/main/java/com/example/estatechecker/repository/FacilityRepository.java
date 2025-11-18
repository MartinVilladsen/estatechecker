package com.example.estatechecker.repository;

import com.example.estatechecker.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {
}
