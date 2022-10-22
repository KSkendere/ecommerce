package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.AddressDto;
import com.kristineskendere.ecommerceapp.models.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AddressMapper {

    AddressDto addressEntityToDto(Address address);
    Address addressDtoToEntity(AddressDto addressDto);

    List<AddressDto>toEntity(List<Address> addresses);
    List<Address>toDto(List<AddressDto>addressDtos);


}
