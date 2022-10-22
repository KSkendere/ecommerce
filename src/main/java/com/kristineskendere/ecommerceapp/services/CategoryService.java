package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id) throws RecordNotFoundException;

    List<CategoryDto> getCategories();

    void deleteCategory(Long id);
}
