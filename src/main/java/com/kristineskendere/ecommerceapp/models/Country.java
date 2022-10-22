package com.kristineskendere.ecommerceapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
//@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Country code is required.")
    @Size(min = 3, max = 3, message = "Country code must be between 3 characters long")
    @Column(name = "code",unique = true)
    private String code;
    @NotBlank(message = "Country name is required.")
    @Column(name = "name",unique = true)
    private String name;
    @OneToMany (mappedBy = "country")
    private List<State> state;

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

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }
}