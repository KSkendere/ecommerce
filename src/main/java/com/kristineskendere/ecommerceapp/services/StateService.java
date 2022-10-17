package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.StateDto;

import java.util.List;

public interface StateService {
    List<StateDto> findAllStates();

    List<StateDto> findAllStatesByCountryCode(String countryCode);

    StateDto findStateById(int id);
}
