package com.kristineskendere.ecommerceapp.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.controllers.CountryController;
import com.kristineskendere.ecommerceapp.controllers.ProductController;
import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.models.Category;
import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import com.kristineskendere.ecommerceapp.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(value = "admin")
@WebMvcTest(ProductController.class)
public class ProductIT {

    public static String URL = "/api/ecommerce/products";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserRepository userRepository;


    @WithUnauthenticatedMockUser
    @Test
     void testCreateProduct() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("test category");

//        when(jwtTokenUtil.getUserNameFromToken(Mockito.any())).thenReturn("admin");

        when(jwtTokenUtil.getUserNameFromToken(Mockito.any())).thenReturn("admin");


        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("test");
        productDto.setActive(true);
        productDto.setImageUrl("testImageUrl");
        productDto.setDescription("test description");
        productDto.setSku("testSku");
        productDto.setUnitPrice(BigDecimal.valueOf(10));
        productDto.setUnitPrice(BigDecimal.valueOf(10));
        productDto.setCategory(category);

//        when(productService.saveProduct(Mockito.any())).thenReturn(productDto);

        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)

                        .content(asJsonString(productDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated());

    }


    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


