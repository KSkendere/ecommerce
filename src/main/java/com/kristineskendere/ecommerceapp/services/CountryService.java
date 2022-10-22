package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAllCountries();

    CountryDto findById(int id) throws ChangeSetPersister.NotFoundException, RecordNotFoundException;

    CountryDto saveCountry(CountryDto countryDto);

    CountryDto updateCountry(int id, CountryDto countryDto);

    void deleteCountry(int id);
}
