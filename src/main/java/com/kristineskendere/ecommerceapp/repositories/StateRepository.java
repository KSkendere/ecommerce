package com.kristineskendere.ecommerceapp.repositories;

import com.kristineskendere.ecommerceapp.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    List<State> findAllByCountryCode(String countryCode);
}
