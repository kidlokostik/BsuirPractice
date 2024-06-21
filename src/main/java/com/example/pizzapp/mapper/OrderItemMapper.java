package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper{
    OrderItem createRequestToEntity(OrderItemCreateRequest orderItemCreateRequest);

    OrderItemResponse toResponse(OrderItem orderItem);
}

