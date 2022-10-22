package com.kristineskendere.ecommerceapp.web.rest;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import com.kristineskendere.ecommerceapp.services.CountryService;
import com.kristineskendere.ecommerceapp.services.CountryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountryIT {


    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryMapper countryMapper;
    @InjectMocks
    private CountryServiceImpl countryServiceImpl;

        @Test
        public void testFindAllCountries () {

            Country country1 = createCountry();
            Country country2 = createSecondCountry();
            CountryDto countryDto1 = createCountryDto();
            CountryDto countryDto2 = createSecondCountryDto();
            List<Country> countriesList = new ArrayList<>();
            countriesList.add(country1);
            countriesList.add(country2);
            when(countryRepository.findAll()).thenReturn(countriesList);

            when(countryMapper.countryEntityToDto(country1)).thenReturn(countryDto1);
            when(countryMapper.countryEntityToDto(country2)).thenReturn(countryDto2);
            List<CountryDto> allCountries = countryServiceImpl.findAllCountries();
            assertThat(allCountries).hasSize(2);
            assertThat(allCountries.get(0).getId()).isEqualTo(1);
            assertThat(allCountries.get(0).getName()).isEqualTo("Test");
            assertThat(allCountries.get(0).getCode()).isEqualTo("T1");
            assertThat(allCountries.get(1).getId()).isEqualTo(2);
            assertThat(allCountries.get(1).getName()).isEqualTo("Test2");
            assertThat(allCountries.get(1).getCode()).isEqualTo("T2");
        }




    private Country createCountry() {

        Country country = new Country();
        country.setId(1);
        country.setCode("T1");
        country.setName("Test");

        return country;

    }


    private CountryDto createCountryDto() {

        CountryDto countryDto = new CountryDto();
        countryDto.setId(1);
        countryDto.setCode("T1");
        countryDto.setName("Test");

        return countryDto;

    }

    private Country createSecondCountry() {


        Country country = new Country();
        country.setId(1);
        country.setCode("T1");
        country.setName("Test");

        return country;

    }

    private CountryDto createSecondCountryDto() {


        CountryDto countryDto = new CountryDto();
        countryDto.setId(2);
        countryDto.setCode("T2");
        countryDto.setName("Test2");

        return countryDto;

    }
}
