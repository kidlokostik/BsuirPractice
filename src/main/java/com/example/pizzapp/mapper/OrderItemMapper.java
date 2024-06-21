package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper{
    OrderItem createRequestToEntity(OrderItemCreateRequest orderItemCreateRequest);

    OrderItemResponse toResponse(OrderItem orderItem);
}

