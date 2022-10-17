package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.models.Country;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto countryEntityToDto (Country country);
    Country countryDtoToEntity(CountryDto countryDto);

    List<CountryDto> toEntity(List<Country> countries);
    List<Country>toDto(List<CountryDto>countriesDto);
}
