package com.kristineskendere.ecommerceapp.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Size(min = 3, max = 3, message = "Country code must be between 3 characters long")
    private String code;
    @NotBlank(message = "Country name is required.")
    private String name;
//@JsonIgnore
    private List<State> state;

    public CountryDto() {


    }



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

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }
}

