package com.kristineskendere.ecommerceapp.dtos.mappers;

import com.kristineskendere.ecommerceapp.dtos.OrderDto;
import com.kristineskendere.ecommerceapp.dtos.OrderItemDto;
import com.kristineskendere.ecommerceapp.models.Order;
import com.kristineskendere.ecommerceapp.models.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderEntityToDto (Order order);
    Order orderDtoToEntity (OrderDto orderDto);

    List<OrderDto> toDto (List<Order>order);
    List<Order>toEntity(List<OrderDto>orderDtos);
}
