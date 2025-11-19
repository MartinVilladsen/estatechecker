package com.example.estatechecker.model.wrapper;

import com.example.estatechecker.model.dto.BoligaEstateDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/** Wrapper for BoligaEstate, i am ignoring meta data from results */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BoligaApiResults(List<BoligaEstateDto> results) {
}
