package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper{
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem createRequestToEntity(OrderItemCreateRequest orderItemCreateRequest);

    List<OrderItem> createRequestToEntity(List<OrderItemCreateRequest> orderItemCreateRequests);

    OrderItemResponse toResponse(OrderItem orderItem);

    List<OrderItemResponse> toResponse(List<OrderItem> orderItems);
}

