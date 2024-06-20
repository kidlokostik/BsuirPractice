package com.example.pizzapp.service;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.models.OrderItem;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderCreateRequest createOrderRequest);

    void deleteOrder(Long id);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    void addOrderItem(Long orderId, OrderItemCreateRequest orderItemCreateRequest);

    void addOrderItems(Long orderId, List<OrderItemCreateRequest> orderItemCreateRequests);
}
