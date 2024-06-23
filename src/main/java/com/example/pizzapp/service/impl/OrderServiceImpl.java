package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.OrderMapper;
import com.example.pizzapp.mapper.OrderItemMapper;
import com.example.pizzapp.model.Order;
import com.example.pizzapp.model.OrderItem;
import com.example.pizzapp.repository.OrderItemRepository;
import com.example.pizzapp.repository.OrderRepository;
import com.example.pizzapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;

import java.util.List;
import java.util.stream.Collectors;

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
    public OrderResponse updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order order = findOrderByIdOrThrow(id);
        orderMapper.updateOrderFromUpdateRequest(orderUpdateRequest,order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void addOrderItem(Long orderId, OrderItemCreateRequest orderItemCreateRequest) {
        Order order = findOrderByIdOrThrow(orderId);
        OrderItem orderItem = orderItemMapper.createRequestToEntity(orderItemCreateRequest);
        order.addOrderItem(orderItem);
        orderRepository.save(order);
    }

    @Override
    public void addOrderItems(Long orderId, List<OrderItemCreateRequest> orderItemCreateRequests) {
        Order order = findOrderByIdOrThrow(orderId);
        List<OrderItem> items = orderItemCreateRequests.stream()
                .map(request -> buildOrderItem(order, request))
                .collect(Collectors.toList());
        order.getOrderItems().addAll(items);
        orderRepository.save(order);
        items.stream()
                .map(orderItemMapper::toResponse)
                .collect(Collectors.toList());
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

    private OrderItem buildOrderItem(Order order, OrderItemCreateRequest request) {
        OrderItem orderItem = orderItemMapper.createRequestToEntity(request);
        orderItem.setOrder(order);
        return orderItem;
    }

    private Order findOrderByIdOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Order", id))
                );
    }
}
