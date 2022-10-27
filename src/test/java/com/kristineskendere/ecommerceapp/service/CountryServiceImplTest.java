package com.kristineskendere.ecommerceapp.service;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import com.kristineskendere.ecommerceapp.services.CountryServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CountryServiceImplTest {
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryMapper countryMapper;
    @InjectMocks
    private CountryServiceImpl countryServiceImpl;

    private  Country country1;
    private Country country2;
    private  CountryDto countryDto1;
    private CountryDto countryDto2;

    @BeforeEach
    public void setup(){
        country1 = createCountry();
        country2 = createSecondCountry();
        countryDto1 = createCountryDto();
        countryDto2 = createSecondCountryDto();

    }

    @Test
     void TestFindAllCountries () {

        List<Country> countriesList = new ArrayList<>();
        countriesList.add(country1);
        countriesList.add(country2);
        when(countryRepository.findAll()).thenReturn(countriesList);
        when(countryMapper.countryEntityToDto(country1)).thenReturn(countryDto1);
        when(countryMapper.countryEntityToDto(country2)).thenReturn(countryDto2);
        List<CountryDto> allCountries = countryServiceImpl.findAllCountries();
        verify(countryRepository, times(1)).findAll();
        assertThat(allCountries).hasSize(2);
        assertThat(allCountries.get(0).getId()).isEqualTo(1);
        assertThat(allCountries.get(0).getName()).isEqualTo("Test");
        assertThat(allCountries.get(0).getCode()).isEqualTo("T1");
        assertThat(allCountries.get(1).getId()).isEqualTo(2);
        assertThat(allCountries.get(1).getName()).isEqualTo("Test2");
        assertThat(allCountries.get(1).getCode()).isEqualTo("T2");
    }

    @Test
     void findById() throws RecordNotFoundException {
        when(countryRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(country1));
        when(countryMapper.countryEntityToDto(Mockito.any())).thenReturn(countryDto1);
        CountryDto foundCountryDto = countryServiceImpl.findById(1);
        verify(countryRepository, times(1)).findById(Mockito.anyInt());
        assertThat(foundCountryDto.getName()).isEqualTo("Test");
        assertThat(foundCountryDto.getCode()).isEqualTo("T1");
    }

    @Test
    void saveCountry() {
        when(countryRepository.save(Mockito.any())).thenReturn(country1);
        when(countryMapper.countryDtoToEntity(Mockito.any())).thenReturn(country1);
        when(countryMapper.countryEntityToDto(Mockito.any())).thenReturn(countryDto1);
        CountryDto savedCountryDto = countryServiceImpl.saveCountry(countryDto1);
        verify(countryRepository, times(1)).save(Mockito.any());
        assertThat(savedCountryDto.getId()).isEqualTo(1);
        assertThat(savedCountryDto.getName()).isEqualTo("Test");
        assertThat(savedCountryDto.getCode()).isEqualTo("T1");
    }

    @Test
    void updateCountry() {
        when(countryRepository.save(Mockito.any())).thenReturn(country1);
        when(countryMapper.countryDtoToEntity(Mockito.any())).thenReturn(country1);
        when(countryMapper.countryEntityToDto(Mockito.any())).thenReturn(countryDto1);
        CountryDto updateCountryDto = countryServiceImpl.updateCountry(1, countryDto1);
        verify(countryRepository, times(1)).save(Mockito.any());
        assertThat(updateCountryDto.getId()).isEqualTo(country1.getId());
        assertThat(updateCountryDto.getName()).isEqualTo(country1.getName());
        assertThat(updateCountryDto.getCode()).isEqualTo(country1.getCode());
    }

    @Test
     void deleteCountry() {
        countryServiceImpl.deleteCountry(1);
        verify(countryRepository, times(1)).deleteById(Mockito.anyInt());
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
