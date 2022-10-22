package com.kristineskendere.ecommerceapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kristineskendere.ecommerceapp.models.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDto {

    private Long id;
    @NotBlank(message = "Category name is required.")
    private String categoryName;
    @JsonIgnore
    private List<Product> products;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}