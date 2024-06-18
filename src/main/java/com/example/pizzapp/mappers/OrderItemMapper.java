package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderItemDto;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "orderItem.order.id", target = "order")
    @Mapping(source = "orderItem.product.id", target = "product")
    OrderItemDto toDto(OrderItem orderItem);
    List<OrderItemDto> toDto(List<OrderItem> orderItems);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDto orderItemDto);
}
