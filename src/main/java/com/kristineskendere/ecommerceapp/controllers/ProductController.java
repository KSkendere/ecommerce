package com.kristineskendere.ecommerceapp.controllers;


import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.ProductNotFoundException;
import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping(value = { "/products"})
//    public @NotNull ResponseEntity<List<Product>> getProducts() {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.status(HttpStatus.OK).body(products);
//    }

    @GetMapping(value = { "/products"})
    public  ResponseEntity<Page<Product>> getProductsWithPagination(@NotNull @RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Product> products = productService.findProductWithPagination(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(value = { "/products/{id}"})
    public  Product getProduct(@NotNull @PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping(value = { "/products"})
    public ResponseEntity<ProductDto> saveProduct(@NotNull @RequestBody ProductDto productDto) {

        ProductDto productDtoSaved = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoSaved);
    }

//    @GetMapping(value = { "/products/categoryId/{id}"})
//    public @NotNull ResponseEntity <List<Product>>getProductByCategoryId(@PathVariable Long id) {
//    List<Product> products = productService.getProductByCategoryId(id);
//
//    return ResponseEntity.status(HttpStatus.OK).body(products);
//    }

    @GetMapping(value = { "/products/categoryId/{id}"})
    public ResponseEntity <Page<Product>>getProductByCategoryIdWithPagination(@NotNull @PathVariable Long id, @RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Product> products = productService.getProductByCategoryIdWithPagination(id, pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(value = { "/products/searchname"})
    public  ResponseEntity<Page<Product>> getProductsBySearchName(@NotNull @RequestParam String searchName, @RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Product> products = productService.findProductBySearchName(searchName,pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


    @DeleteMapping(value={"/products/{id}"})
    public ResponseEntity<Void>  deleteProduct(@NotNull @PathVariable Long id){

    productService.deleteProduct(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value={"/products/{id}"})
    public ResponseEntity<ProductDto>  UpdateProduct(@NotNull @PathVariable Long id, @RequestBody ProductDto productDto  ){

      ProductDto productDtoSaved = productService.updateProduct(id, productDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}




