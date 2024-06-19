package com.example.pizzapp.services;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.dto.ProductDto;
import com.example.pizzapp.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    Order createOrder(Order createOrderRequest);
    Order updateOrder(Order updateOrderRequest);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    List<OrderDto> getAllOrders();
}
