package com.kristineskendere.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.controllers.CountryController;
import com.kristineskendere.ecommerceapp.controllers.ProductController;
import com.kristineskendere.ecommerceapp.dtos.CountryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import com.kristineskendere.ecommerceapp.services.CountryService;
import com.kristineskendere.ecommerceapp.services.CountryServiceImpl;
import com.kristineskendere.ecommerceapp.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails
@WebMvcTest(CountryController.class)
public class CountryControllerTest {


    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    public static final String URL = "/api/ecommerce/countries";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CountryService countryService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    UserRepository userRepository;



    CountryDto countryDto;
    CountryDto updatedCountryDto;
    CountryDto secondCountryDto;
    List<CountryDto> countriesDtoList;


    @BeforeEach
    public void init() {
        countryDto = createCountryDto();
        countriesDtoList = creteCountryDtoList();
    }

    @Test
    public void testFindAllCountries() throws Exception {
        when(countryService.findAllCountries()).thenReturn(countriesDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", equalToIgnoringCase("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", equalToIgnoringCase("T1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", equalToIgnoringCase("Test2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code", equalToIgnoringCase("T2")));
        verify(countryService, times(1)).findAllCountries();
    }

    @Test
    public void getCountry() throws Exception {
        when(countryService.findById(Mockito.anyInt())).thenReturn(countryDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/1"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code",equalToIgnoringCase("T1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalToIgnoringCase("Test")));
        verify(countryService, times(1)).findById(Mockito.anyInt());
    }

    @Test
    public void saveCountryTest() throws Exception {
        secondCountryDto = createSecondCountryDto();
        when(countryService.saveCountry(Mockito.any())).thenReturn(secondCountryDto);
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .with((csrf()))
                .content(asJsonString(secondCountryDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", equalToIgnoringCase("T2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalToIgnoringCase("Test2")));
    }

    @Test
    public void updateCountryTest() throws Exception {
        updatedCountryDto = createUpdatedCountryDto();
        when(countryService.updateCountry(Mockito.anyInt(), Mockito.any())).thenReturn(updatedCountryDto);
        mockMvc.perform(MockMvcRequestBuilders
                .put(URL + "/1")
                .with((csrf()))
                .content(asJsonString(updatedCountryDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", equalToIgnoringCase("T3")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalToIgnoringCase("Test3")));
        verify(countryService, times(1)).updateCountry(Mockito.anyInt(), Mockito.any());
    }

    private CountryDto createUpdatedCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(3);
        countryDto.setCode("T3");
        countryDto.setName("Test3");

        return countryDto;

    }


    private CountryDto createCountryDto() {

        CountryDto countryDto = new CountryDto();
        countryDto.setId(1);
        countryDto.setCode("T1");
        countryDto.setName("Test");

        return countryDto;

    }


    private CountryDto createSecondCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(2);
        countryDto.setCode("T2");
        countryDto.setName("Test2");
        return countryDto;

    }

    private List<CountryDto> creteCountryDtoList() {
        List<CountryDto> countriesList = new ArrayList<>();
        countriesList.add(createCountryDto());
        countriesList.add(createSecondCountryDto());
        return countriesList;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
