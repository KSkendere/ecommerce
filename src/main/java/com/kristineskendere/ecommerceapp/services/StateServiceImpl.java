package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.StateMapper;
import com.kristineskendere.ecommerceapp.models.State;
import com.kristineskendere.ecommerceapp.repositories.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;
    private StateMapper stateMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    @Override
    public List<StateDto> findAllStates() {

        List<State>states = stateRepository.findAll();
        return states.stream().map(state-> stateMapper.stateEntityToDto(state)).collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findAllStatesByCountryCode(String countryCode) {
        List<State> findAllStatesByCountryCode = stateRepository.findAllByCountryCode(countryCode);
        return findAllStatesByCountryCode.stream().map(state -> stateMapper.stateEntityToDto(state)).collect(Collectors.toList());
    }

    @Override
    public StateDto findStateById(int id) {
        Optional<State> state = stateRepository.findById(id);
        return stateMapper.stateEntityToDto(state.get());
    }
}
