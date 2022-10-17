package com.kristineskendere.ecommerceapp.repositories;

import com.kristineskendere.ecommerceapp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategoryId(Long id);

//    @Query(value ="SELECT * FROM Product p WHERE p.category = ?1 ",
//            countQuery = "select count(id) from Product p  WHERE p.category = ?1",
//
//            nativeQuery = true)
//    Page<Product> findByCategoryIdWithPagination(Long id, int pageSize, int pageNo);

    Page<Product> findByCategoryId(Long id, Pageable pageable);

    Page<Product> findByNameContaining(String searchName, Pageable page);
}
