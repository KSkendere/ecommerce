package com.kristineskendere.ecommerceapp.dtos;

import com.kristineskendere.ecommerceapp.models.Address;
import com.kristineskendere.ecommerceapp.models.Customer;
import com.kristineskendere.ecommerceapp.models.Order;
import com.kristineskendere.ecommerceapp.models.OrderItem;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;


public class PurchaseDto {
    @NotNull(message = "Customer is required.")
    private Customer customer;
    @NotNull(message = "Shipping Address is required.")
    private Address shippingAddress;
    @NotNull(message = "Billing Adress is required.")
    private Address billingAddress;
    @NotNull(message = "Order is required.")
    private Order order;
    @NotNull(message = "Order items are required.")
    private Set<OrderItem> orderItems;

    public PurchaseDto() {
    }

    public PurchaseDto(Customer customer, Address shippingAddress, Address billingAddress, Order order, Set<OrderItem> orderItems) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
