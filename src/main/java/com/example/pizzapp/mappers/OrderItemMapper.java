package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderItemDTO;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItem orderItem);
    List<OrderItemDTO> toDTO(List<OrderItem> orderItems);

    OrderItem toEntity(OrderItemDTO orderItemDTO);
}
