package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "order.user.id", target = "user")
    OrderDto toDto(Order order);
    List<OrderDto> toDto(List<Order> orders);
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDto orderDto);
}
