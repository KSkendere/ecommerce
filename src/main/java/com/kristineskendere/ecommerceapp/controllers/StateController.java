package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.services.StateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")

public class StateController {

    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @GetMapping(value = { "/states"})
    ResponseEntity<List<StateDto>>findAllStates(){

        List<StateDto> statesDtos = stateService.findAllStates();

        return ResponseEntity.ok(statesDtos);
    }
    @GetMapping(value = { "/states/countryCode"})
    ResponseEntity<List<StateDto>>findAllStatesByCountryCode(@RequestParam String countryCode){

        List<StateDto>stateDtosByCountryCode = stateService.findAllStatesByCountryCode(countryCode);

        return ResponseEntity.ok(stateDtosByCountryCode);
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = { "/states/{id}"})
    ResponseEntity <StateDto> findStateById(@PathVariable int id){

        StateDto stateDto = stateService.findStateById(id);
        return ResponseEntity.ok(stateDto);

    }

}
