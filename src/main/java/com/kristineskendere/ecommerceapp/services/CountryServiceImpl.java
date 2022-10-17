package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional
public class CountryServiceImpl  implements CountryService{

    private CountryRepository countryRepository;

    private CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    @Transactional
    public List<CountryDto> findAllCountries() {
        List<Country>countryList=countryRepository.findAll();
        return countryList.stream().map(country -> countryMapper.countryEntityToDto(country)).collect(Collectors.toList());
//        return countryList.stream().map(country -> countryMapper.countryEntityToDto(country)).collect(Collectors.toList());
    }

    @Override
    public CountryDto findById(int id) throws ChangeSetPersister.NotFoundException {

        Optional<Country> country = countryRepository.findById(id);
        return countryMapper.countryEntityToDto( country.get());
    }


}
