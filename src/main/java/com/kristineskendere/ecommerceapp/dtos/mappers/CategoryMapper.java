package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.AddressDto;
import com.kristineskendere.ecommerceapp.dtos.CategoryDto;
import com.kristineskendere.ecommerceapp.models.Address;
import com.kristineskendere.ecommerceapp.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryEntityToDto(Category category);
    Category categoryDtoToEntity(CategoryDto categoryDto);

    List<CategoryDto> categoryEntityToDtoList(List<Category> categories);
    List<Category>categoryDtoToEntityList(List<CategoryDto>categoriesDto);
}
