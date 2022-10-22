package com.kristineskendere.ecommerceapp.controllers;


import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.ProductNotFoundException;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
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

//    @GetMapping(value = { "/products/categoryId/{id}"})
//    public @NotNull ResponseEntity <List<Product>>getProductByCategoryId(@PathVariable Long id) {
//    List<Product> products = productService.getProductByCategoryId(id);
//
//    return ResponseEntity.status(HttpStatus.OK).body(products);
//    }

    @GetMapping(value = { "/products/categoryId/{id}"})@NonNull
    public ResponseEntity <Page<ProductDto>>getProductByCategoryIdWithPagination(@NonNull @PathVariable Long id, @NonNull @RequestParam int pageNo, @NonNull @RequestParam int pageSize) {
//        Page<ProductDto> products = productService.getProductByCategoryIdWithPagination(id, pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryIdWithPagination(id, pageNo,pageSize));
    }

    @GetMapping(value = { "/products/searchname"})
    public  ResponseEntity<Page<ProductDto>> getProductsBySearchName(@NonNull @RequestParam String searchName, @NonNull @RequestParam int pageNo, @NonNull @RequestParam int pageSize) {
        Page<ProductDto> productsDto = productService.findProductBySearchName(searchName,pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value={"/products/admin/{id}"})
    public ResponseEntity<ProductDto>  deleteProduct(@NonNull @PathVariable Long id) throws RecordNotFoundException, ProductNotFoundException {

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





