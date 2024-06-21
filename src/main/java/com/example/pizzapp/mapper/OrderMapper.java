package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "user", ignore = true)
    Order createRequestToEntity(OrderCreateRequest orderCreateRequest);

    @Mapping(source = "order.user.id", target = "userId")
    OrderResponse toResponse(Order order);
}
