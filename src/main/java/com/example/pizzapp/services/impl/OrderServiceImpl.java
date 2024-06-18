package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.mappers.OrderMapper;
import com.example.pizzapp.models.Order;
import com.example.pizzapp.repositories.OrderRepository;
import com.example.pizzapp.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    @Override
    public Order createProduct(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public Order updateProduct(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
