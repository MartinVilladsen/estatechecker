package com.example.estatechecker.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BoligaEstateDto(
        String address,
        int zipCode,
        int price,
        OffsetDateTime soldDate,
        int propertyType,
        String saleType,
        double sqmPrice,
        Integer rooms,
        Integer size,
        Integer buildYear,
        double change,
        String guid,
        double latitude,
        double longitude,
        Integer municipalityCode,
        Integer estateCode,
        String city,
        String groupKey,
        Boolean canGetVR,
        Long bfEnr,
        Long ouId,
        String ouAddress
) {}

