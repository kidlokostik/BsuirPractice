package com.example.pizzapp.services;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderCreateRequest createOrderRequest);
    OrderResponse updateOrder(OrderUpdateRequest updateOrderRequest);
    void deleteOrder(Long id);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();
}
