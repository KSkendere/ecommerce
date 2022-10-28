package com.kristineskendere.ecommerceapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;
import com.kristineskendere.ecommerceapp.repositories.CategoryRepository;
import com.kristineskendere.ecommerceapp.repositories.ProductRepository;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {
        "INSERT INTO Product_category(id,category_name)VALUES(1,'Category Name')",
        "INSERT INTO Product_category(id,category_name)VALUES(2,'Category Name2')",
        "INSERT INTO Product(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(2,1,'AC','TEST','Description',10.00, 'image_url', true,10,null,null)",
        "INSERT INTO Product(id,category_id,sku,name,description,unit_price,image_url, active,units_in_stock,date_created,last_updated) VALUES(3,2,'AC1','OTHER','Description1',10.00, 'image_url1', true,10,null,null)"
})
 class ProductIT {

    private static final Logger log = LoggerFactory.getLogger(ProductIT.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserRepository userRepository;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    @BeforeEach
    void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/ecommerce/products");
    }

    @Test
    @Transactional
    void testGetProduct() throws Exception {
        mockMvc.perform(get(baseUrl + "/2"))
                .andExpect(status().isOk());
        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    @Transactional
    void testGetProductByID_productIsNotPresent_ExceptionIsThrown() throws Exception {
        mockMvc.perform(get(baseUrl + "/5"))
                .andExpect(status().isNotFound())
                .andExpect(result-> Assertions.assertTrue(result.getResolvedException()instanceof RecordNotFoundException))
                .andExpect(result-> Assertions.assertEquals("Product not Found", result.getResolvedException().getMessage()));
    }

    @Test
    @Transactional
//    @Sql(statements = {"DELETE FROM Product_category", "DELETE FROM Product_category"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetAllProductsWithPagination() throws Exception {
        mockMvc.perform(get(baseUrl + "/?pageNo=0&pageSize=5"))
                .andExpect(status().isOk());
        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    @Transactional
    void getProductByCategoryIdWithPagination() throws Exception {
        ResultActions result = mockMvc.perform(get(baseUrl + "/categoryId/2?pageNo=0&pageSize=5"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));


        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    @Transactional
    void testGetProductByCategoryIdWithPagination_CategoryIsNotPresent_ExceptionIsThrown() throws Exception {
        mockMvc.perform(get(baseUrl + "/categoryId/5?pageNo=0&pageSize=5"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isNotFound())
                .andExpect(result-> Assertions.assertTrue(result.getResolvedException()instanceof RecordNotFoundException))
                .andExpect(result-> Assertions.assertEquals("Category with such id not found", result.getResolvedException().getMessage()));
    }

    @Test
    @Transactional
    void getProductsBySearchName() throws Exception {
        mockMvc.perform(get(baseUrl + "/searchname?pageNo=0&pageSize=5&searchName=TEST"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
        assertEquals(2, productRepository.findAll().size());
    }

    @WithMockUser(username = "test", password = "test", roles = "admin")
    @Test
    @Transactional
    void testDeleteProductsById() throws Exception {
        mockMvc.perform(delete(baseUrl + "/admin/2"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk());
        assertEquals(1, productRepository.findAll().size());
    }

    @WithMockUser(username = "test", password = "test", roles = "admin")
    @Test
    @Transactional
    void testDeleteProductsByID_productIsNotPresent_ExpectException() throws Exception {
        mockMvc.perform(delete(baseUrl + "/admin/5"))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isNotFound())
                .andExpect(result-> Assertions.assertTrue(result.getResolvedException()instanceof RecordNotFoundException))
                .andExpect(result-> Assertions.assertEquals("Product not Found", result.getResolvedException().getMessage()));
    }

    @WithMockUser(username = "test", password = "test", roles = "admin")
    @Test
    @Transactional
    void saveProduct() throws Exception {
        assertEquals(2, productRepository.findAll().size());
        ProductDto productDto = createProductDto();
        mockMvc.perform(post(baseUrl + "/admin")
                .with(csrf())
                .content(asJsonString(productDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name", equalToIgnoringCase(productDto.getName())))
                .andExpect(jsonPath("$.active").value(productDto.isActive()))
                .andExpect(jsonPath("$.imageUrl", equalToIgnoringCase(productDto.getImageUrl())))
                .andExpect(jsonPath("$.sku", equalToIgnoringCase(productDto.getSku())))
                .andExpect(jsonPath("$.unitPrice").value(productDto.getUnitPrice()))
                .andExpect(jsonPath("$.category.id").value(productDto.getCategory().getId()))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                );
        assertEquals(3, productRepository.findAll().size());
    }

    @WithMockUser(username = "test", password = "test", roles = "admin")
    @Test
    @Transactional
    void updateProduct() throws Exception {

        assertEquals(2, productRepository.findAll().size());

        ProductDto updatedProductDto = createProductDto();
        updatedProductDto.setId(3L);

        mockMvc.perform(put(baseUrl + "/admin/3")
                .with(csrf())
                .content(asJsonString(updatedProductDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedProductDto.getId()))
                .andExpect(jsonPath("$.name", equalToIgnoringCase(updatedProductDto.getName())))
                .andExpect(jsonPath("$.active").value(updatedProductDto.isActive()))
                .andExpect(jsonPath("$.imageUrl", equalToIgnoringCase(updatedProductDto.getImageUrl())))
                .andExpect(jsonPath("$.sku", equalToIgnoringCase(updatedProductDto.getSku())))
                .andExpect(jsonPath("$.unitPrice").value(updatedProductDto.getUnitPrice()))
                .andExpect(jsonPath("$.category.id").value(updatedProductDto.getCategory().getId()))
                .andExpect(
                        mvcResult -> {
                            log.info("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                );
        assertEquals(2, productRepository.findAll().size());
    }

    private ProductDto createProductDto() {

        ProductDto productDto = new ProductDto();
        productDto.setName("saved product");
        productDto.setActive(true);
        productDto.setImageUrl("saved testImageUrl");
        productDto.setDescription("saved test description");
        productDto.setSku("saved test Sku");
        productDto.setUnitPrice(BigDecimal.valueOf(10));
        productDto.setUnitsInStock(10);
        productDto.setCategory(categoryRepository.findById(1L).get());
        return productDto;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

