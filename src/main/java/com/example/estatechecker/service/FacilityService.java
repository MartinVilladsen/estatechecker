package com.example.estatechecker.service;

import com.example.estatechecker.model.dto.FacilityDto;
import com.example.estatechecker.model.entity.Facility;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface FacilityService {
    List<FacilityDto> findFacilities(double lat, double lon, int radius);

    List<Facility> saveFacilities(double lat, double lon, int radius);
}
