package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.services.CountryService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/ecommerce")

public class CountryController {

    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping(value = { "/countries"})
    public ResponseEntity<List<CountryDto>>findAllCountries(){
        List<CountryDto>countriesList = countryService.findAllCountries();
        return ResponseEntity.ok(countriesList);
    }

    @GetMapping (value = { "/countries/{id}"})
    public ResponseEntity<CountryDto>findCountryById(@NonNull @PathVariable int id) throws ChangeSetPersister.NotFoundException, RecordNotFoundException {
        CountryDto countryDto = countryService.findById(id);
        return ResponseEntity.ok(countryDto);
    }

    @PostMapping(value = { "/countries"})
    public ResponseEntity<CountryDto>saveCountry(@Valid  @RequestBody CountryDto countryDto){
        CountryDto savedCountryDto = countryService.saveCountry(countryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountryDto);
    }
    @PutMapping(value = { "/countries/{id}"})
    public ResponseEntity<CountryDto>updateCountry(@NonNull @PathVariable int id, @Valid @RequestBody CountryDto countryDto){
        CountryDto updatedCountryDto = countryService.updateCountry(id, countryDto);
        return ResponseEntity.ok(updatedCountryDto);
    }

    @DeleteMapping(value = { "/countries/{id}"})
    public ResponseEntity<CountryDto>updateCountry(@NonNull @PathVariable int id) throws RecordNotFoundException, ChangeSetPersister.NotFoundException {
        CountryDto foundCountryDto = countryService.findById(id);
        countryService.deleteCountry(id);
        return ResponseEntity.ok(foundCountryDto);
    }
}
