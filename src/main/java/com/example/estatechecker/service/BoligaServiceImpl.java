package com.example.estatechecker.service;

import com.example.estatechecker.mapper.BoligaEstateMapper;
import com.example.estatechecker.model.dto.BoligaEstateDto;
import com.example.estatechecker.model.entity.BoligaEstate;
import com.example.estatechecker.repository.BoligaEstateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.MappingException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BoligaServiceImpl implements BoligaService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper;
    private final BoligaEstateRepository repository;

    public BoligaServiceImpl(
            ObjectMapper mapper,
            BoligaEstateRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<BoligaEstate> findByZipCode(int zipCode) {
        List<BoligaEstate> estates = new ArrayList<>();

        try {
            String url = String.format(
                    "https://api.boliga.dk/api/v2/sold/search/results?page=1&pageSize=5000&zipCode=%d",
                    zipCode
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode root = mapper.readTree(response.body());
            JsonNode results = root.get("results");

            if (results == null || !results.isArray()) {
                log.warn("No results for zip {}", zipCode);
                return estates;
            }

            for (JsonNode node : results) {
                BoligaEstateDto dto = mapper.treeToValue(node, BoligaEstateDto.class);
                if (dto.zipCode() != zipCode) {
                    continue;
                }

                BoligaEstate estate = BoligaEstateMapper.toEntity(dto);

                estates.add(estate);
            }

        } catch (MappingException e) {
            log.error("Dto object is null. Error: {} ", e.getMessage());
            throw new MappingException("BoligaEstateDto is null", e);
        }
        catch (Exception e) {
            // TODO: Make custom exception and throw here. I should also add GlobalExceptionHandler
            log.error("Error fetching data", e);
        }

        return estates;
    }


    @Override
    public List<BoligaEstate> saveByZipCode(int zipCode) {

        List<BoligaEstate> fetched = findByZipCode(zipCode);
        List<BoligaEstate> toSave = new ArrayList<>();

        for (BoligaEstate estate : fetched) {
            UUID id = estate.getId();

            if (repository.existsById(id)) {
                log.warn("Duplicate estate skipped. GUID already exists: {}", id);
                continue;
            }

            toSave.add(estate);
        }

        if (toSave.isEmpty()) {
            log.info("No new estates to save for zip {}", zipCode);
            return List.of();
        }

        return repository.saveAll(toSave);
    }
}
