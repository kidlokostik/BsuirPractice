package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderDTO;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTO(List<Order> orders);

    Order toEntity(OrderDTO orderDTO);
}
