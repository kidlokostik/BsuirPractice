package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.OrderItemDto;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderItemUpdateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "orderItem.order.id", target = "orderId")
    @Mapping(source = "orderItem.product.id", target = "productId")
    OrderItemCreateRequest toCreateRequest(OrderItem orderItem);
    List<OrderItemCreateRequest> toCreateRequest(List<OrderItem> orderItems);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem createRequestToEntity(OrderItemCreateRequest orderItemCreateRequest);

    @Mapping(source = "orderItem.order.id", target = "orderId")
    @Mapping(source = "orderItem.product.id", target = "productId")
    OrderItemUpdateRequest toUpdateRequest(OrderItem orderItem);
    List<OrderItem> createRequestToEntity(List<OrderItemCreateRequest> orderItemCreateRequests);
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem updateRequestToEntity(OrderItemUpdateRequest orderItemUpdateRequest);

    @Mapping(source = "orderItem.order.id", target = "orderId")
    @Mapping(source = "orderItem.product.id", target = "productId")
    OrderItemResponse toResponse(OrderItem orderItem);
    List<OrderItem> updateRequestToEntity(List<OrderItemUpdateRequest> orderItemUpdateRequests);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem responseToEntity(OrderItemResponse orderItemResponse);
    List<OrderItem> responseToEntity(List<OrderItemResponse> orderItemResponses);
}

