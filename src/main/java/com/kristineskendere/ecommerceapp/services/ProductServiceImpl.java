package com.kristineskendere.ecommerceapp.services;


import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.ProductMapper;
import com.kristineskendere.ecommerceapp.exceptions.ProductNotFoundException;
import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    private ProductMapper productMapper;


    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("product not Found"));
//        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = productMapper.productDtoToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productEntityToDto(savedProduct);
    }


    @Override
    public List<Product> getProductByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }
    @Override
    public Page<Product> findProductWithPagination(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        return productRepository.findAll(page);
    }

    @Override
    public Page<Product> getProductByCategoryIdWithPagination(Long id, int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        return productRepository.findByCategoryId(id, page);
    }

    @Override
    public Page<Product> findProductBySearchName(String searchName, int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        return productRepository.findByNameContaining(searchName, page);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product savedProduct = productRepository.save(productMapper.productDtoToEntity(productDto));
        return(productMapper.productEntityToDto(savedProduct));

    }
}
