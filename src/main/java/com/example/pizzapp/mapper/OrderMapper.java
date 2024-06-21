package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "user", ignore = true)
    Order createRequestToEntity(OrderCreateRequest orderCreateRequest);

    Order updateRequestToEntity(Long id, OrderUpdateRequest orderUpdateRequest);

    @Mapping(source = "order.user.id", target = "userId")
    OrderResponse toResponse(Order order);
}
