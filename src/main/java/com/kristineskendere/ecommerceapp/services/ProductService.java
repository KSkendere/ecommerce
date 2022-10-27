package com.kristineskendere.ecommerceapp.services;
import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    ProductDto getProductDtoById(Long id) throws  RecordNotFoundException;

    ProductDto saveProduct(ProductDto product);

    List<Product> getProductByCategoryId(Long id);

    Page<ProductDto> findProductWithPagination(int pageNo, int pageSize);

    Page<ProductDto> getProductByCategoryIdWithPagination(Long id, int pageNo, int pageSize);

    Page<ProductDto> findProductBySearchName(String searchName, int pageNo, int pageSize);

    void deleteProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);
}

