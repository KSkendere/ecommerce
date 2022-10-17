package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAllCountries();

    CountryDto findById(int id) throws ChangeSetPersister.NotFoundException;
}
