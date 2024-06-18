package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.dto.OrderItemDto;
import com.example.pizzapp.mappers.OrderItemMapper;
import com.example.pizzapp.models.OrderItem;
import com.example.pizzapp.repositories.OrderItemRepository;
import com.example.pizzapp.services.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    private OrderItemMapper orderItemMapper;
    @Override
    public OrderItem createOrder(OrderItem order) {
        return orderItemRepository.createOrder(order);
    }

    @Override
    public OrderItem updateOrder(Long id, OrderItem order) {
        return orderItemRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
     orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem getOrderById(Long id) {
        return orderItemRepository.getById(id);
    }

    @Override
    public List<OrderItemDto> getAllOrders() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }
}

