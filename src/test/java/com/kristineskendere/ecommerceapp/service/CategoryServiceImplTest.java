package com.kristineskendere.ecommerceapp.service;

import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CategoryMapper;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Category;
import com.kristineskendere.ecommerceapp.repositories.CategoryRepository;
import com.kristineskendere.ecommerceapp.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    private Category category1;
    private Category category2;
    private CategoryDto categoryDto1;
    private CategoryDto categoryDto2;
    private List <Category> categories;
    private List <CategoryDto>categoriesDto;
    private List <Category> emptyCategories;
    private List <CategoryDto> emptyCategoriesDto;

    @BeforeEach
    public void setup(){
        category1 = createCategory();
        category2 = createCategory1();
        categoryDto1 = createCategoryDto();
        categoryDto2 = createCategoryDto2();
        categories = createCategoryList();
        categoriesDto = createCategoryDtoList();
        emptyCategories = new ArrayList<>();
        emptyCategoriesDto = new ArrayList<>();
    }

    @Test
    void testSaveCategory(){
        when(categoryRepository.save(Mockito.any())).thenReturn(category1);
        when(categoryMapper.categoryDtoToEntity(Mockito.any())).thenReturn(category1);
        when(categoryMapper.categoryEntityToDto(Mockito.any())).thenReturn(categoryDto1);
        CategoryDto savedCategoryDto = categoryServiceImpl.saveCategory(categoryDto1);
        assertThat(savedCategoryDto.getId()).isEqualTo(category1.getId());
        assertThat(savedCategoryDto.getCategoryName()).isEqualTo(category1.getCategoryName());
        verify(categoryRepository,times(1)).save(Mockito.any());
    }

    @Test
    void testSaveCategory_IfPassedInInformationNotCorrect_ThenIllegalArgumentExceptionIsThrown(){
        when(categoryRepository.save(Mockito.any())).thenThrow(new IllegalArgumentException());
        when(categoryMapper.categoryDtoToEntity(categoryDto1)).thenReturn(category1);
        assertThrows(IllegalArgumentException.class, ()->categoryServiceImpl.saveCategory(categoryDto1));
        verify(categoryRepository,times(1)).save(Mockito.any());
    }

    @Test
    void testGetCategory() throws RecordNotFoundException {
        when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category1));
        when(categoryMapper.categoryEntityToDto(Mockito.any())).thenReturn(categoryDto1);
        CategoryDto foundCategory = categoryServiceImpl.getCategory(1L);
        assertThat(foundCategory.getId()).isEqualTo(category1.getId());
        assertThat(foundCategory.getCategoryName()).isEqualTo(category1.getCategoryName());
        verify(categoryRepository,times(1)).findById(Mockito.anyLong());
    }

    @Test
    void testGetCategory_IfNotExistingCategoryId_ThrowRecordNotFoundExceptionException() {
        when(categoryRepository.findById(null)).thenThrow(new RuntimeException());
        assertThrows(RecordNotFoundException.class, ()->categoryServiceImpl.getCategory(Mockito.anyLong()));
        verify(categoryRepository,times(1)).findById(Mockito.anyLong());
    }

    @Test
    void testGetCategory_IfThereIsNothingToReturn_ThrowRecordNotFoundExceptionException() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, ()->categoryServiceImpl.getCategory(Mockito.anyLong()));
        verify(categoryRepository,times(1)).findById(Mockito.anyLong());
    }

    @Test
    void testGetCategories()  {
        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.categoryEntityToDtoList(Mockito.any())).thenReturn(categoriesDto);
        List<CategoryDto> foundCategories = categoryServiceImpl.getCategories();
        assertThat(foundCategories).hasSize(2);
        assertThat(foundCategories.get(0).getId()).isEqualTo(category1.getId());
        assertThat(foundCategories.get(0).getCategoryName()).isEqualTo(category1.getCategoryName());
        assertThat(foundCategories.get(1).getId()).isEqualTo(category2.getId());
        assertThat(foundCategories.get(1).getCategoryName()).isEqualTo(category2.getCategoryName());
        verify(categoryRepository,times(1)).findAll();
    }

    @Test
    void testGetCategories_ifThereIsNoCategories_EmptyListIsReturned()  {
        when(categoryRepository.findAll()).thenReturn(emptyCategories);
        when(categoryMapper.categoryEntityToDtoList(Mockito.any())).thenReturn(emptyCategoriesDto);
        List<CategoryDto> foundCategories = categoryServiceImpl.getCategories();
        assertThat(foundCategories).isEmpty();
        verify(categoryRepository,times(1)).findAll();
    }

    @Test
    void testDeleteCategories() {
        categoryServiceImpl.deleteCategory(Mockito.anyLong());
        verify(categoryRepository,times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void tesDeleteCategory_IfNotExistingCategoryId_ThrowIllegalArgumentException() {
        doThrow(new IllegalArgumentException()).when(categoryRepository).deleteById(null);
        assertThrows(IllegalArgumentException.class, ()->categoryServiceImpl.deleteCategory(null));
        verify(categoryRepository,times(1)).deleteById(null);
    }

    private Category createCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("TestCategory1");
        return category;
    }

    private Category createCategory1(){
        Category category = new Category();
        category.setId(2L);
        category.setCategoryName("TestCategory2");
        return category;
    }

    private Category createEmptyCategory(){
        Category category = new Category();
        category.setId(null);
        category.setCategoryName(null);
        return category;
    }

    private CategoryDto createCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setCategoryName("TestCategory1");
        return categoryDto;
    }

    private CategoryDto createCategoryDto2(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(2L);
        categoryDto.setCategoryName("TestCategory2");
        return categoryDto;
    }

    private List<Category> createCategoryList(){
        List<Category>categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        return categoryList;
    }

    private List<CategoryDto> createCategoryDtoList(){
        List<CategoryDto>categoryDtoList = new ArrayList<>();
        categoryDtoList.add(categoryDto1);
        categoryDtoList.add(categoryDto2);
        return categoryDtoList;
    }

}
