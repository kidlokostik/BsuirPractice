package com.example.pizzapp.services;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    OrderItemResponse createOrderItem(OrderItemCreateRequest orderItemCreateRequest);
    OrderItemResponse updateOrderItem(Long id, OrderUpdateRequest updateOrderRequest);
    void deleteOrderItem(Long id);
    OrderItemResponse getOrderItemById(Long id);
    List<OrderItemResponse> getAllOrderItems();
}
