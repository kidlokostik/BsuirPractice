package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.OrderMapper;
import com.example.pizzapp.mapper.OrderItemMapper;
import com.example.pizzapp.models.Order;
import com.example.pizzapp.models.OrderItem;
import com.example.pizzapp.repositories.OrderItemRepository;
import com.example.pizzapp.repositories.OrderRepository;
import com.example.pizzapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderResponse createOrder(OrderCreateRequest createOrderRequest) {
        Order order = orderMapper.createRequestToEntity(createOrderRequest);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderItemResponse addOrderItem(Long id, OrderItemCreateRequest orderItemCreateRequest) {
        Order newestOrder = findOrderByIdOrThrow(id);
        OrderItem orderItem = orderItemMapper.createRequestToEntity(orderItemCreateRequest);
        orderItem.setOrder(newestOrder);
        return orderItemMapper.toResponse(orderItemRepository.save(orderItem));
    }

    @Override
    public List<OrderItemResponse> addOrderItems(Long id, List<OrderItemCreateRequest> orderItemCreateRequests) {
        Order newestOrder = findOrderByIdOrThrow(id);
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItemCreateRequest orderItemCreateRequest : orderItemCreateRequests) {
            OrderItem orderItem = orderItemMapper.createRequestToEntity(orderItemCreateRequest);
            orderItem.setOrder(newestOrder);
            orderItemResponses.add(orderItemMapper.toResponse(orderItem));
        }
        return orderItemResponses;
    }

    private Order findOrderByIdOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = findOrderByIdOrThrow(id);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
