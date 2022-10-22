package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.CustomerDto;
import com.kristineskendere.ecommerceapp.models.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto customerEntityToDto(Customer customer);
    Customer customerDtoToEntity(CustomerDto customerDto);
    List<CustomerDto> toEntity(List<Customer> customers);
    List<Customer>toDto(List<CustomerDto>customerDtos);

}
