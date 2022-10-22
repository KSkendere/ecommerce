package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    @Transactional
    public List<CountryDto> findAllCountries() {
        List<Country> countryList = countryRepository.findAll();
        return countryList.stream().map(country -> countryMapper.countryEntityToDto(country)).collect(Collectors.toList());
    }

    @Override
    public CountryDto findById(int id) throws RecordNotFoundException {
        Country country = countryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Country with such id not found"));
        return countryMapper.countryEntityToDto(country);
    }

    @Override
    public CountryDto saveCountry(CountryDto countryDto) {
        Country savedCountry = countryRepository.save(countryMapper.countryDtoToEntity(countryDto));
        return countryMapper.countryEntityToDto(savedCountry);
    }

    @Override
    public CountryDto updateCountry(int id, CountryDto countryDto) {
        Country updatedCountry = countryRepository.save(countryMapper.countryDtoToEntity(countryDto));
        return countryMapper.countryEntityToDto(updatedCountry);
    }

    @Override
    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }
}
