package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderDTO;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "order.user.id", target = "user")
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTO(List<Order> orders);
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDTO orderDTO);
}
