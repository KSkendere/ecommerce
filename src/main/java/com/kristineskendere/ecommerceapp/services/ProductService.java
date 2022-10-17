package com.kristineskendere.ecommerceapp.services;
import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.ProductNotFoundException;
import com.kristineskendere.ecommerceapp.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id) throws ProductNotFoundException;

    ProductDto saveProduct(ProductDto product);

    List<Product> getProductByCategoryId(Long id);

    Page<Product> findProductWithPagination(int pageNo, int pageSize);

    Page<Product> getProductByCategoryIdWithPagination(Long id, int pageNo, int pageSize);

    Page<Product> findProductBySearchName(String searchName, int pageNo, int pageSize);

    void deleteProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);
}
