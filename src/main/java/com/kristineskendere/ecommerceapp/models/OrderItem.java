package com.kristineskendere.ecommerceapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")


public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    @NotBlank(message = "Product image url is required.")
    @Column(name = "image_url")
    private String imageUrl;
    @NotNull(message = " Product unit price is required.")
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @NotNull(message = "Product quantity is required.")
    @Column(name = "quantity")
    private int quantity;
    @NotNull(message = "Product id is required.")
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne
    @JoinColumn(name="order_id")
    @NotNull(message = "Product order is required.")
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
