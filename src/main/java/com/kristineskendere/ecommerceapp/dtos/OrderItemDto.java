package com.kristineskendere.ecommerceapp.dtos;

import com.kristineskendere.ecommerceapp.models.Order;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemDto {


    private Long id;
    @NotBlank(message = "Product image url is required.")
    private String imageUrl;
    @NotNull(message = " Product unit price is required.")
    private BigDecimal unitPrice;
    @NotNull(message = " Product quantity is required.")
    private int quantity;
    @NotNull(message = "Product id is required.")
    private Long productId;
    //    @ManyToOne
//    @JoinColumn(name="order_id")
    @NotNull(message = "Product order is required.")
    private OrderDto orderdto;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String imageUrl, BigDecimal unitPrice, int quantity, Long productId, OrderDto orderdto) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.productId = productId;
        this.orderdto = orderdto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderDto getOrderdto() {
        return orderdto;
    }

    public void setOrderdto(OrderDto orderdto) {
        this.orderdto = orderdto;
    }
}
