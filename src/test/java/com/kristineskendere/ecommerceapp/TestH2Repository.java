package com.kristineskendere.ecommerceapp;

import com.kristineskendere.ecommerceapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product, Long> {
}
