package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;

import java.util.List;

public interface StateService {
    List<StateDto> findAllStates();
    List<StateDto> findAllStatesByCountryCode(String countryCode);
    StateDto findStateById(int id) throws RecordNotFoundException;

    StateDto saveState(StateDto stateDto);

    StateDto updateState(int id, StateDto stateDto);

    void deleteState(int id);
}
