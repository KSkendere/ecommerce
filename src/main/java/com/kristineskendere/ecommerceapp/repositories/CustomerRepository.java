package com.kristineskendere.ecommerceapp.repositories;

import com.kristineskendere.ecommerceapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
