package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.StateMapper;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.State;
import com.kristineskendere.ecommerceapp.repositories.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return stateMapper.entityToDtoList(states);
    }

    @Override
    public List<StateDto> findAllStatesByCountryCode(String countryCode) {
        List<State> findAllStatesByCountryCode = stateRepository.findAllByCountryCode(countryCode);
        return stateMapper.entityToDtoList(findAllStatesByCountryCode);
    }

    @Override
    public StateDto findStateById(int id) throws RecordNotFoundException {
        State state = stateRepository.findById(id).orElseThrow(()->new RecordNotFoundException("State with such id not found"));
        return stateMapper.stateEntityToDto(state);
    }

    @Override
    public StateDto saveState(StateDto stateDto) {
        State state = stateRepository.save(stateMapper.stateDtoToEntity(stateDto));
        return stateMapper.stateEntityToDto(state);
    }

    @Override
    public StateDto updateState(int id, StateDto stateDto) {
        State state = stateRepository.save(stateMapper.stateDtoToEntity(stateDto));
        return stateMapper.stateEntityToDto(state);
    }

    @Override
    public void deleteState(int id) {
        stateRepository.deleteById(id);

    }
}
