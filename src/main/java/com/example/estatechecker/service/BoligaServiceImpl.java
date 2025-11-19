package com.example.estatechecker.service;

import com.example.estatechecker.mapper.BoligaEstateMapper;
import com.example.estatechecker.model.entity.BoligaEstate;
import com.example.estatechecker.model.wrapper.BoligaApiResults;
import com.example.estatechecker.repository.BoligaEstateRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public BoligaServiceImpl(ObjectMapper mapper, BoligaEstateRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    // TODO: Add javadoc for methods

    @Override
    public List<BoligaEstate> findByZipCode(int zipCode) {
        try {
            String url = "https://api.boliga.dk/api/v2/sold/search/results?page=1&pageSize=5000&zipCode=" + zipCode;

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET()
                    .header("Accept", "application/json").build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            BoligaApiResults apiResults = mapper.readValue(response.body(), BoligaApiResults.class);

            if (apiResults.results() == null || apiResults.results().isEmpty()) {
                log.debug("No results for zip {}", zipCode);
                return List.of();
            }

            return apiResults.results().stream().filter(dto -> dto.zipCode() == zipCode)
                    .map(BoligaEstateMapper::toEntity).toList();
        } catch (MappingException e) {
            log.error("Dto object is null. Error: {}", e.getMessage());
            throw new MappingException("BoligaEstateDto is null", e);
        } catch (Exception e) {
            log.error("Error fetching data", e);
            return List.of();
        }
    }

    @Override
    public List<BoligaEstate> saveByZipCode(int zipCode) {

        List<BoligaEstate> fetched = findByZipCode(zipCode);
        List<BoligaEstate> saved = new ArrayList<>();

        for (BoligaEstate estate : fetched) {
            UUID id = estate.getId();

            if (repository.existsById(id)) {
                log.warn("Duplicate estate skipped. GUID already exists: {}", id);
                continue;
            }

            saved.add(estate);
        }

        if (saved.isEmpty()) {
            log.info("No new estates to save for zip {}", zipCode);
            return List.of();
        }

        return repository.saveAll(saved);
    }
}
