package com.kristineskendere.ecommerceapp.controllers;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.services.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")

public class StateController {

    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @GetMapping(value = { "/states"})
    public ResponseEntity<List<StateDto>>findAllStates(){

        List<StateDto> statesDtos = stateService.findAllStates();

        return ResponseEntity.ok(statesDtos);
    }
    @GetMapping(value = { "/states/countryCode"})
    public ResponseEntity<List<StateDto>>findAllStatesByCountryCode(@Valid @RequestParam String countryCode){

        List<StateDto>stateDtosByCountryCode = stateService.findAllStatesByCountryCode(countryCode);

        return ResponseEntity.ok(stateDtosByCountryCode);
    }
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = { "/states/{id}"})
    public ResponseEntity <StateDto> findStateById(@NonNull @PathVariable int id) throws RecordNotFoundException {

        StateDto stateDto = stateService.findStateById(id);
        return ResponseEntity.ok(stateDto);
    }

    @PostMapping (value = { "/states"})
    public ResponseEntity<StateDto> saveState(@Valid @RequestBody StateDto stateDto){
        StateDto savedStateDto = stateService.saveState(stateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStateDto);
    }

    @PutMapping (value = { "/states/{id}"})
    public ResponseEntity<StateDto> updateState(@NonNull @PathVariable int id, @Valid @RequestBody StateDto stateDto){
        StateDto updatedStateDto = stateService.updateState(id,stateDto);
        return ResponseEntity.ok(updatedStateDto);
    }

    @DeleteMapping (value = {"/states/{id}"})
    public ResponseEntity<StateDto> deleteState(@NonNull @PathVariable int id) throws RecordNotFoundException {
        StateDto foundStateDto = stateService.findStateById(id);
        stateService.deleteState(id);
        return ResponseEntity.ok(foundStateDto);
    }

}
