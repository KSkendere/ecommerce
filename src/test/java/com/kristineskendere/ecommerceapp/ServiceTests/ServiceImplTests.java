package com.kristineskendere.ecommerceapp.ServiceTests;

import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.repositories.ProductRepository;
import com.kristineskendere.ecommerceapp.services.ProductService;
import com.kristineskendere.ecommerceapp.services.ProductServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ServiceImplTests {
////    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();
//
//    @Before
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//    @MockBean
//    ProductRepository productRepository;
//   @Autowired
//    ProductService productService;
//
//
//
//    static final Product createProduct() {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Product1");
//        product.setPrice(2.12);
//        product.setPictureUrl("Picture1");
//        return product;
//    }
//
//    static final Product createProduct2() {
//        Product product1 = new Product();
//        product1.setId(2L);
//        product1.setName("Product2");
//        product1.setPrice(2.12);
//        product1.setPictureUrl("Picture3");
//        return product1;
//    }


//    @Test
//
//   void testGetAllProducts(){
//       List<Product> products = Arrays.asList(createProduct(), createProduct2());
//       when(productRepository.findAll()).thenReturn(products);
//       List<Product> allProducts = productService.getAllProducts();
//       assertEquals(2,allProducts.size());
//        assertEquals("Product1",allProducts.get(0).getName());
//        assertEquals(2.12,allProducts.get(0).getPrice());
//        assertEquals(1,allProducts.get(0).getId());
//        assertEquals("Picture1",allProducts.get(0).getPictureUrl());
//   }
//}
