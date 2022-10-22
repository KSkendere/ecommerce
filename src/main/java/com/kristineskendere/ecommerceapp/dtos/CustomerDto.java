package com.kristineskendere.ecommerceapp.dtos;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class CustomerDto{

    private Long id;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @Email
    @NotBlank(message = "Email is required.")

    private String email;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
@NotNull(message = "Orders are required")
    private Set<OrderDto> orderDtos = new HashSet<>();

    public CustomerDto() {
    }

    public CustomerDto(Long id, String firstName, String lastName, String email, Set<OrderDto> orderDtos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.orderDtos = orderDtos;
    }
    public void addOrderDto(OrderDto orderDto){
        if(orderDto!=null){
            if(orderDtos==null){
                orderDtos = new HashSet<>();
            }
            orderDtos.add(orderDto);
            orderDto.setCustomerDto(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public void setOrderDtos(Set<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
    }
}