package com.kristineskendere.ecommerceapp.repositories;

import com.kristineskendere.ecommerceapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
