package com.kristineskendere.ecommerceapp.dtos;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class OrderDto{

    private Long id;
    @NotBlank(message = "Order tracker number is required.")
    private String orderTrackerNumber;
    @NotNull(message = "Total quantity is required.")
    private int totalQuantity;
    @NotNull(message = "Total price is required.")
    private BigDecimal totalPrice;
    private String status;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;
    @NotNull(message = "Order Items are required.")
    private Set<OrderItemDto> orderItemsDtos = new HashSet<>();
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
@NotNull(message = "Customer is required.")
    private CustomerDto customerDto;
//    @OneToOne(cascade = CascadeType.ALL)
@NotNull(message = "Shipping Address is required.")
    private AddressDto shippingAddressDto;
//    @OneToOne(cascade = CascadeType.ALL)
@NotNull(message = "Billing Address is required.")
    private AddressDto billingAddressDto;

    public OrderDto() {
    }
    public OrderDto(Long id,
                    String orderTrackerNumber
            , int totalQuantity,
                    BigDecimal totalPrice,
                    String status,
                    LocalDate dateCreated,
                    LocalDate lastUpdated,
                    Set<OrderItemDto> orderItemsDtos,
                    CustomerDto customerDto,
                    AddressDto shippingAddressDto,
                    AddressDto billingAddressDto) {
        this.id = id;
        this.orderTrackerNumber = orderTrackerNumber;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.orderItemsDtos = orderItemsDtos;
        this.customerDto = customerDto;
        this.shippingAddressDto = shippingAddressDto;
        this.billingAddressDto = billingAddressDto;
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

    public Set<OrderItemDto> getOrderItemsDtos() {
        return orderItemsDtos;
    }

    public void setOrderItemsDtos(Set<OrderItemDto> orderItemsDtos) {
        this.orderItemsDtos = orderItemsDtos;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public AddressDto getShippingAddressDto() {
        return shippingAddressDto;
    }

    public void setShippingAddressDto(AddressDto shippingAddressDto) {
        this.shippingAddressDto = shippingAddressDto;
    }

    public AddressDto getBillingAddressDto() {
        return billingAddressDto;
    }

    public void setBillingAddressDto(AddressDto billingAddressDto) {
        this.billingAddressDto = billingAddressDto;
    }

    public void add(OrderItemDto itemDto) {

        if (itemDto != null) {
            if (orderItemsDtos == null) {
                orderItemsDtos = new HashSet<>();
            }
            orderItemsDtos.add(itemDto);
            itemDto.setOrderdto(this);
        }
    }
}


