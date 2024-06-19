package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.OrderDto;
import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.OrderMapper;
import com.example.pizzapp.models.Category;
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

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderCreateRequest createOrderRequest) {
        Order order = orderMapper.createRequestToEntity(createOrderRequest);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderUpdateRequest updateOrderRequest) {
        Order newestOrder = findOrderByOrThrow(id);
        orderMapper.toUpdateRequest(newestOrder);

        Order updateOrder = orderRepository.save(newestOrder);
        return orderMapper.toResponse(updateOrder);
    }

    private Order findOrderByOrThrow(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = findOrderByOrThrow(id);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
