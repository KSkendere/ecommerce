package com.kristineskendere.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.jwt.JwtTokenUtil;

import com.kristineskendere.ecommerceapp.repositories.authRepositories.UserRepository;
import com.kristineskendere.ecommerceapp.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WithMockUser(username = "test", password = "test", roles = "admin")
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CategoryControllerTest.class);

    private static final String URL = "/api/ecommerce/categories";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryController categoryController;

    @MockBean
    CategoryService categoryService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @MockBean
    UserRepository userRepository;

    CategoryDto categoryDto;
    CategoryDto categoryDto2;
    List<CategoryDto> categoryDtos;

    @BeforeEach
    public void init(){

        categoryDto = createCategoryDto();
        categoryDto2 = createCategoryDto2();
        categoryDtos = createCategoryDtoList();
    }

    @Test
    public void getCategoryTest() throws Exception {

        when(categoryService.getCategory(Mockito.anyLong())).thenReturn(categoryDto);
         mockMvc.perform(MockMvcRequestBuilders.get(URL+"/1"))
                .andExpect(
                        mvcResult ->{
                            log.info ("CONTENT {}", mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                         .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName",equalToIgnoringCase("Test")));

         verify(categoryService, times(1)).getCategory(Mockito.anyLong());
    }




    @Test
    public void getCategoriesTest() throws Exception {
        when(categoryService.getCategories()).thenReturn(categoryDtos);
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(
                        mvcResult ->{
                            System.out.println("CONTENT" +mvcResult.getResponse().getContentAsString());
                        }
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].categoryName",equalToIgnoringCase("Test")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].categoryName",equalToIgnoringCase("Test2")));
        verify(categoryService, times(1)).getCategories();
    }

    @Test
    public void saveCategoryTest() throws Exception {
        when(categoryService.saveCategory(Mockito.any())).thenReturn(categoryDto);
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .with((csrf()))
                .content(asJsonString(categoryDto))
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

    private CategoryDto createCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setCategoryName("Test");
        return categoryDto;
    }


    private CategoryDto createCategoryDto2(){
        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setId(2L);
        categoryDto2.setCategoryName("Test2");
        return categoryDto2;
    }

    private List <CategoryDto> createCategoryDtoList(){

        List <CategoryDto> categoryDtos = new ArrayList<>();

        categoryDtos.add(createCategoryDto());
        categoryDtos.add(createCategoryDto2());
        return categoryDtos;
    }

}
