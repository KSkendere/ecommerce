package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.models.State;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface StateMapper {

    StateDto stateEntityToDto(State state);

    State stateDtoToEntity(StateDto stateDto);

    List<StateDto> entityToDtoList(List<State> states);
    List<State>dtoToEntityList(List<StateDto>statesDto);
}
