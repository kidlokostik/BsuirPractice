package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderItemUpdateRequest;
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

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem updateRequestToEntity(OrderItemUpdateRequest orderItemUpdateRequest);

    List<OrderItem> updateRequestToEntity(List<OrderItemUpdateRequest> orderItemUpdateRequests);

    @Mapping(source = "orderItem.order.id", target = "orderId")
    @Mapping(source = "orderItem.product.id", target = "productId")
    OrderItemResponse toResponse(OrderItem orderItem);

    List<OrderItemResponse> toResponse(List<OrderItem> orderItems);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem responseToEntity(OrderItemResponse orderItemResponse);

    List<OrderItem> responseToEntity(List<OrderItemResponse> orderItemResponses);
}

