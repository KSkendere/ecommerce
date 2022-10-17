package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.services.CountryService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")

public class CountryController {

    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping(value = { "/countries"})
    ResponseEntity<List<CountryDto>>findAllCountries(){

        List<CountryDto>countriesList = countryService.findAllCountries();

        return ResponseEntity.ok(countriesList);

    }
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping (value = { "/countries/{id}"})
    ResponseEntity<CountryDto>findCountryById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        CountryDto countryDto = countryService.findById(id);
        return ResponseEntity.ok(countryDto);
    }


}
