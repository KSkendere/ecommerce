package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.models.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface StateMapper {
@Mapping(source="country", target="countryDto")
    StateDto stateEntityToDto(State state);
    @Mapping(source="countryDto", target="country")
    State stateDtoToEntity(StateDto stateDto);
    @Mapping(source="country", target="countryDto")
    List<StateDto> entityToDtoList(List<State> states);
    @Mapping(source="countryDto", target="country")
    List<State> dtoToEntityList(List<StateDto> statesDto);
}