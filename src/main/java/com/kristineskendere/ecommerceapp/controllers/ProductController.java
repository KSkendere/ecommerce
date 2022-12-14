package com.kristineskendere.ecommerceapp.controllers;


import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.services.CategoryService;
import com.kristineskendere.ecommerceapp.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

    ProductService productService;
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = { "/products"})
    public  ResponseEntity<Page<ProductDto>> getProductsWithPagination(@NonNull @RequestParam int pageNo, @NonNull@RequestParam int pageSize) {
        Page<ProductDto> productsDto = productService.findProductWithPagination(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @GetMapping(value = { "/products/{id}"})
    public  ProductDto getProduct(@NonNull @PathVariable Long id)  throws RecordNotFoundException {
        return productService.getProductDtoById(id);
    }
        @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value = { "/products/admin"})
    public ResponseEntity<ProductDto> saveProduct( @Valid  @RequestBody ProductDto productDto) {
        ProductDto productDtoSaved = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoSaved);
    }

    @GetMapping(value = {"/products/categoryId/{id}"})
    @NonNull
    public ResponseEntity<Page<ProductDto>> getProductByCategoryIdWithPagination(@NonNull @PathVariable Long id, @NonNull @RequestParam int pageNo, @NonNull @RequestParam int pageSize) throws RecordNotFoundException {
        categoryService.getCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryIdWithPagination(id, pageNo, pageSize));
    }

    @GetMapping(value = {"/products/searchname"})
    public ResponseEntity<Page<ProductDto>> getProductsBySearchName(@NonNull @RequestParam String searchName, @NonNull @RequestParam int pageNo, @NonNull @RequestParam int pageSize) {
        Page<ProductDto> productsDto = productService.findProductBySearchName(searchName, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value = {"/products/admin/{id}"})
    public ResponseEntity<ProductDto> deleteProduct(@NonNull @PathVariable Long id) throws RecordNotFoundException {
        ProductDto productDto = productService.getProductDtoById(id);
        productService.deleteProduct(id);
        return ResponseEntity.ok(productDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping(value={"/products/admin/{id}"})
    public ResponseEntity<ProductDto>  updateProduct(@NonNull @PathVariable Long id, @Valid @RequestBody ProductDto productDto  ){
        ProductDto productDtoSaved = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(productDtoSaved);
    }
}





