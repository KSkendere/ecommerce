package com.kristineskendere.ecommerceapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="state", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "country_id" }) })
public class State {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotBlank(message = "State name is required.")
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}