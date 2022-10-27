package com.kristineskendere.ecommerceapp.dtos;
import com.kristineskendere.ecommerceapp.models.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    @NotNull (message = "Product category is required.")
    private Category category;
    @NotEmpty(message = "Product sku name is required.")
    @Size(min = 2, max = 24, message = "Product sku must be between 2 and 24 characters long")
    private String sku;
    @NotBlank(message = "Product name is required.")
    private String name;
    @NotBlank(message = "Product description is required.")
    private String description;
    @NotNull(message = "Unit price is required.")
    private BigDecimal unitPrice;
    private String imageUrl;

    private boolean active;
    @NotNull(message= "Units in stock may not be empty")
    private int unitsInStock;


    public ProductDto(Long id, Category category, String sku, String name, String description, BigDecimal unitPrice, String imageUrl, boolean active, int unitsInStock) {
        this.id = id;
        this.category = category;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.active = active;
        this.unitsInStock = unitsInStock;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

}
