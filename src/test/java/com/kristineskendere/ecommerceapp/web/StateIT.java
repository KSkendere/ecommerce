package com.kristineskendere.ecommerceapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CountryMapper;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.models.Country;
import com.kristineskendere.ecommerceapp.repositories.CountryRepository;
import com.kristineskendere.ecommerceapp.repositories.StateRepository;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Sql(statements = {
        "INSERT INTO Country(id,code,name)VALUES(1,'T1','Country Name1')",
        "INSERT INTO Country(id,code,name)VALUES(2,'T2','Country Name2')",
        "INSERT INTO State(id,name,country_id)VALUES(2,'State Name1',1)",
        "INSERT INTO State(id,name,country_id)VALUES(3,'State Name2',2)",
})
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StateIT {

    private static final Logger log = LoggerFactory.getLogger(StateIT.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserRepository userRepository;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @BeforeEach
    void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/ecommerce/states");
    }

@Test
@Transactional
void testFindAllStates() throws Exception {
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
}

    @Test
    @Transactional
    void testFindAllStatesByCountryCode() throws Exception {
        assertThat(stateRepository.findAll()).hasSize(2);
        mockMvc.perform(get(baseUrl+"/countryCode?countryCode=T1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    void testFindStateById() throws Exception {
        assertThat(stateRepository.findAll()).hasSize(2);
        mockMvc.perform(get(baseUrl + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name", equalToIgnoringCase("State Name1")))
                .andExpect(jsonPath("$.countryDto.id").value(1))
                .andExpect(jsonPath("$.countryDto.code", equalToIgnoringCase("T1")))
                .andExpect(jsonPath("$.countryDto.name", equalToIgnoringCase("Country Name1")));
    }

    @Test
    @Transactional
    void testSaveState() throws Exception {
        assertThat(stateRepository.findAll()).hasSize(2);
        StateDto stateDto = createStateDto();
        mockMvc.perform(post(baseUrl)
                .with(csrf())
                .content(asJsonString(stateDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name", equalToIgnoringCase(stateDto.getName())))
                .andExpect(jsonPath("$.countryDto.id").value(1))
                .andExpect(jsonPath("$.countryDto.code", equalToIgnoringCase("T1")))
                .andExpect(jsonPath("$.countryDto.name", equalToIgnoringCase("Country Name1")));
        assertThat(stateRepository.findAll()).hasSize(3);
    }

    @Test
    @Transactional
    void testUpdateState() throws Exception {
        assertThat(stateRepository.findAll()).hasSize(2);
        StateDto updatedDto = createStateDto();
        updatedDto.setId(2);
        mockMvc.perform(put(baseUrl + "/2")
                .with(csrf())
                .content(asJsonString(updatedDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name", equalToIgnoringCase(updatedDto.getName())))
                .andExpect(jsonPath("$.countryDto.id").value(1))
                .andExpect(jsonPath("$.countryDto.code", equalToIgnoringCase("T1")))
                .andExpect(jsonPath("$.countryDto.name", equalToIgnoringCase("Country Name1")));
        assertThat(stateRepository.findAll()).hasSize(2);
    }

    @Test
    @Transactional
    void testDeleteState() throws Exception {
        assertThat(stateRepository.findAll()).hasSize(2);
        mockMvc.perform(delete(baseUrl + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name", equalToIgnoringCase("State Name1")))
                .andExpect(jsonPath("$.countryDto.id").value(1))
                .andExpect(jsonPath("$.countryDto.code", equalToIgnoringCase("T1")))
                .andExpect(jsonPath("$.countryDto.name", equalToIgnoringCase("Country Name1")));
        assertThat(stateRepository.findAll()).hasSize(1);
    }

    private StateDto createStateDto() {
        StateDto stateDto = new StateDto();
        stateDto.setName("New State");
        stateDto.setCountryDto(countryMapper.countryEntityToDto(countryRepository.findById(1).get()));
        return stateDto;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




