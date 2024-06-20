package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderItem", ignore = true)
    Order createRequestToEntity(OrderCreateRequest orderCreateRequest);

    List<Order> createRequestToEntity(List<OrderCreateRequest> orderCreateRequests);

    @Mapping(source = "order.user.id", target = "userId")
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponse(List<Order> orders);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderItem", ignore = true)
    Order responseToEntity(OrderResponse orderResponse);

    List<Order> responseToEntity(List<OrderResponse> orderResponses);
}
