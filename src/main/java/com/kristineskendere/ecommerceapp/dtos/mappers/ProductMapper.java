package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.dtos.StateDto;
import com.kristineskendere.ecommerceapp.models.Product;
import com.kristineskendere.ecommerceapp.models.State;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productEntityToDto(Product product);

    Product productDtoToEntity(ProductDto productDto);

    List<ProductDto> entityToDtoList(List<Product> products);
    List<Product>dtoToEntityList(List<ProductDto>productDtos);
}
