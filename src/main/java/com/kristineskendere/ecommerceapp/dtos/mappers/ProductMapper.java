package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.ProductDto;
import com.kristineskendere.ecommerceapp.models.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productEntityToDto(Product product);

    Product productDtoToEntity(ProductDto productDto);

    List<ProductDto> entityToDtoList(List<Product> products);

    List<Product> dtoToEntityList(List<ProductDto> productDtos);

    default Page<ProductDto> entityToDtoPage(Page<Product> productPage) {
        return productPage.map(this::productEntityToDto);
    }

    default Page<Product> DtoToEntityPage(Page<ProductDto> productDtoPage) {
        productDtoPage.map(this::productDtoToEntity);
        return productDtoPage.map(this::productDtoToEntity);
    }
}