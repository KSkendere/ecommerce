package com.kristineskendere.ecommerceapp.repositories;

import com.kristineskendere.ecommerceapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
