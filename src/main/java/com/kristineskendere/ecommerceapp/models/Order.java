package com.kristineskendere.ecommerceapp.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Order tracker number is required.")
    @Column(name = "order_tracker_number")
    private String orderTrackerNumber;
    @NotNull(message = "Total quantity is required.")
    @Column(name = "total_quantity")
    private int totalQuantity;
    @NotNull(message = "Total price is required.")
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "status")
    private String status;
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated;
    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDate lastUpdated;
    @NotNull(message = "Order Items are required.")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();
    @NotNull(message = "Customer is required.")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @NotNull(message = "Shipping Address is required.")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_adress_id", referencedColumnName = "id")
    private Address shippingAddress;
    @NotNull(message = "Billing Address is required.")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_adress_id", referencedColumnName = "id")
    private Address billingAddress;



        public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTrackerNumber() {
        return orderTrackerNumber;
    }

    public void setOrderTrackerNumber(String orderTrackerNumber) {
        this.orderTrackerNumber = orderTrackerNumber;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
