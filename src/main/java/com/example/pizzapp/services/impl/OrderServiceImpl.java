package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.mappers.OrderMapper;
import com.example.pizzapp.models.Order;
import com.example.pizzapp.repositories.OrderRepository;
import com.example.pizzapp.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    @Override
    public OrderResponse createOrder(OrderCreateRequest createOrderRequest) {
        Order order = orderMapper.toEntity(createOrderRequest);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
