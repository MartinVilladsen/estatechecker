package com.example.estatechecker.service;

import com.example.estatechecker.model.entity.BoligaEstate;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface BoligaService {
    List<BoligaEstate> findByZipCode(int zipCode);

    List<BoligaEstate> saveByZipCode(int zipCode);
}
