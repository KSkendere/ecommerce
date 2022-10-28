package com.kristineskendere.ecommerceapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")


public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Street name is required")
    @Column(name = "street")
    private String street;
    @NotBlank(message = "City name is required")
    @Column(name = "city")
    private String city;
    @NotBlank(message = "State name is required")
    @Column(name = "state")
    private String state;
    @NotBlank(message = "Country name is required")
    @Column(name = "country")
    private String country;
    @NotBlank(message = "Zip code is required")
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

