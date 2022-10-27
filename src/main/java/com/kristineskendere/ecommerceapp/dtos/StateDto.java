package com.kristineskendere.ecommerceapp.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kristineskendere.ecommerceapp.models.Country;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

//@Component
//@Data
public class StateDto implements Serializable {

    private int id;
    @NotBlank(message = "State name is required.")
    private String name;
//    @NotEmpty(message = "Country is required.")
//    @JsonIgnoreProperties(value ={"state"}, allowSetters= true)
    private CountryDto countryDto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    @JsonBackReference


    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }
}
