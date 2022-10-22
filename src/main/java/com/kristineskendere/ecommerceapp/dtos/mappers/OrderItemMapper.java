package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.OrderItemDto;
import com.kristineskendere.ecommerceapp.models.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto orderItemEntityToDto (OrderItem orderItem);
    OrderItem orderItemDtoToEntity (OrderItemDto orderItemDto);

    List<OrderItemDto> toEntity (Set<OrderItemDto> orderItems);
    List<OrderItem>toDto(List<OrderItemDto>orderItemDtos);
}
