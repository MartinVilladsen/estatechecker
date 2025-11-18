package com.example.estatechecker.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FacilityDto(
        String type,
        double lat,
        double lon,
        Map<String, String> tags
) {}

