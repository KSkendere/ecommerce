package com.kristineskendere.ecommerceapp.services;

import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.dtos.mappers.CategoryMapper;
import com.kristineskendere.ecommerceapp.exceptions.RecordNotFoundException;
import com.kristineskendere.ecommerceapp.models.Category;
import com.kristineskendere.ecommerceapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToEntity(categoryDto);
        return categoryMapper.categoryEntityToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getCategory(Long id) throws RecordNotFoundException {
        Category categoryToFind = categoryRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Category with such id not found"));
        return categoryMapper.categoryEntityToDto(categoryToFind);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        return categoryMapper.categoryEntityToDtoList(allCategories);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }
}
