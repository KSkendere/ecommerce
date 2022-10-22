package com.kristineskendere.ecommerceapp.dtos;

import com.kristineskendere.ecommerceapp.dtos.OrderDto;
import com.kristineskendere.ecommerceapp.models.Order;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddressDto{

    private Long id;
    @NotBlank(message = "Street name is required")
    private String street;
    @NotBlank(message = "City name is required")
    private String city;
    @NotBlank(message = "State name is required")
    private String state;
    @NotBlank(message = "Country name is required")
    private String country;
    @NotBlank(message = "Zip code is required")
    private String zipCode;
//    @OneToOne
//    @PrimaryKeyJoinColumn
    @NotNull(message = "Order is required")
    private OrderDto orderDto;

    public AddressDto() {
    }

    public AddressDto(Long id, String street, String city, String state, String country, String zipCode, OrderDto orderDto) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.orderDto = orderDto;
    }

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

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
