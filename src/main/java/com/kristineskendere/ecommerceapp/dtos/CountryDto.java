package com.kristineskendere.ecommerceapp.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kristineskendere.ecommerceapp.models.State;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//@Component
//@Data
public class CountryDto implements  Serializable {

    private int id;
    @NotEmpty(message = "Country code is required.")
    @Size(min = 2, max = 2, message = "Country code must 2 characters long")
    private String code;
    @NotBlank(message = "Country name is required.")
    private String name;
//@JsonIgnore
//    @JsonIgnoreProperties(value ={"country"}, allowSetters= true)
    private List<StateDto> stateDto;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference


    public List<StateDto> getStateDto() {
        return stateDto;
    }

    public void setStateDto(List<StateDto> stateDto) {
        this.stateDto = stateDto;
    }
}

