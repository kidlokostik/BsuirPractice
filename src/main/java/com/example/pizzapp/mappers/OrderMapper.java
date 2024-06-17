package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDTO;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTO(List<Order> orders);

    User toEntity(OrderDTO orderDTO);
}
