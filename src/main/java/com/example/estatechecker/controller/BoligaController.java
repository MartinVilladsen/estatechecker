package com.example.estatechecker.controller;

import com.example.estatechecker.model.entity.BoligaEstate;
import com.example.estatechecker.service.BoligaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Boliga", description = "Controller for managing Boliga tasks, such as saving and getting esates by zip codes")
@RestController
@RequestMapping("/api/v1/boliga")
public class BoligaController {

    private final BoligaService boligaService;

    public BoligaController(BoligaService boligaService) {
        this.boligaService = boligaService;
    }

    @GetMapping("/get-by-zipcode")
    public List<BoligaEstate> getByZipCode(@RequestParam int zipCode) {
        return boligaService.findByZipCode(zipCode);
    }

    // TODO: Make operation and api tags
    @PostMapping("/save-by-zipcode")
    public List<BoligaEstate> saveByZipCode(@RequestParam int zipCode) {
        return boligaService.saveByZipCode(zipCode);
    }
}
