package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderItemDTO;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "orderItem.order.id", target = "order")
    @Mapping(source = "orderItem.product.id", target = "product")
    OrderItemDTO toDTO(OrderItem orderItem);
    List<OrderItemDTO> toDTO(List<OrderItem> orderItems);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}
