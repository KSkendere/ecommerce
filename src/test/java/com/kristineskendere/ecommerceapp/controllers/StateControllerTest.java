package com.kristineskendere.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import com.kristineskendere.ecommerceapp.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser

@WebMvcTest(StateController.class)
public class StateControllerTest {

    private static final Logger log = LoggerFactory.getLogger(StateControllerTest.class);

    public static final String URL ="/api/ecommerce/states";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StateService stateService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    UserRepository userRepository;

    private StateDto stateDto;
    private StateDto stateDto2;
    private StateDto updatedStateDto;
    private List<StateDto> statesDtoList;

    @BeforeEach
    public void init(){
        stateDto = createStateDto();
        statesDtoList = createStateDtoList();
    }

    @Test
    void getState() throws Exception {
        when(stateService.findStateById(Mockito.anyInt())).thenReturn(stateDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/1"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalToIgnoringCase("Test")));
        verify(stateService, times(1)).findStateById(Mockito.anyInt());
    }

    @Test
     void getStatesTest() throws Exception {
        when(stateService.findAllStates()).thenReturn(statesDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",equalToIgnoringCase("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",equalToIgnoringCase("Test2")));
        verify(stateService, times(1)).findAllStates();
    }

    @Test
     void saveStateTest() throws Exception {
        stateDto2 = createStateDto2();
        when(stateService.saveState(Mockito.any())).thenReturn(stateDto2);
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .with((csrf()))
                .content(asJsonString(stateDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",equalToIgnoringCase("Test2")));
        verify(stateService, times(1)).saveState(Mockito.any());
    }

    @Test
     void updateStateTest() throws Exception {
        updatedStateDto = createStateDto3();
        when(stateService.updateState(Mockito.anyInt(), Mockito.any())).thenReturn(updatedStateDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put(URL + "/1")
                .with((csrf()))
                .content(asJsonString(stateDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalToIgnoringCase("Test3")));
        verify(stateService, times(1)).updateState(Mockito.anyInt(), Mockito.any());
    }

    @Test
     void findAllStatesByCountryCodeTest() throws Exception {
        when(stateService.findAllStatesByCountryCode(Mockito.any())).thenReturn(statesDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/countryCode?countryCode=TT"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",equalToIgnoringCase("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",equalToIgnoringCase("Test2")));
        verify(stateService, times(1)).findAllStatesByCountryCode(Mockito.any());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StateDto createStateDto(){
        StateDto stateDto = new StateDto();
        stateDto.setId(1);
        stateDto.setName("Test");
        stateDto.setCountryDto(createCountryDto());

        return stateDto;
    }

    private StateDto createStateDto2(){
        StateDto stateDto = new StateDto();
        stateDto.setId(2);
        stateDto.setName("Test2");
        stateDto.setCountryDto(createCountryDto());

        return stateDto;
    }

    private StateDto createStateDto3(){
        StateDto stateDto = new StateDto();
        stateDto.setId(1);
        stateDto.setName("Test3");
        stateDto.setCountryDto(createCountryDto());

        return stateDto;
    }

    private List<StateDto> createStateDtoList(){
        List<StateDto> statesDtoList = new ArrayList<>();
        statesDtoList.add(createStateDto());
        statesDtoList.add(createStateDto2());
        return statesDtoList;
    }

    private CountryDto createCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(1);
        countryDto.setName("Test");
        countryDto.setCode("TT");
        return countryDto;
    }

}
