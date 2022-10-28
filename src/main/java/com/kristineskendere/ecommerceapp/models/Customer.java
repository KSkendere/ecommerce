package com.kristineskendere.ecommerceapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotBlank(message = "First name is required.")

    @Column(name="first_name")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    @Column(name="last_name")
    private String lastName;
    @Email
    @NotBlank(message = "Email is required.")
    @Column(name="email",unique = true)
    private String email;
    @NotNull(message = "Orders are required")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();


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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        if(order!=null){
            if(orders==null){
                orders = new HashSet<>();
            }
            orders.add(order);
order.setCustomer(this);
        }
    }


}
