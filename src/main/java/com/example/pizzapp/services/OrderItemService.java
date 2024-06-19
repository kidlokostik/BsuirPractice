package com.example.pizzapp.services;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.dto.OrderItemDto;
import com.example.pizzapp.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderItemService {
     OrderItem createOrder(OrderItem orderItem);
     OrderItem updateOrder(Long id, OrderItem updateOrder) ;
     void deleteOrder(Long id);
     OrderItem getOrderById(Long id);
     List<OrderItemDto> getAllOrders();

}
