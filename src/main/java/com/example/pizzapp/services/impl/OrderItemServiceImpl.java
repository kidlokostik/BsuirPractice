package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderItemResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.OrderItemMapper;
import com.example.pizzapp.models.OrderItem;
import com.example.pizzapp.repositories.OrderItemRepository;
import com.example.pizzapp.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponse createOrderItem(OrderItemCreateRequest orderItemCreateRequest) {
        OrderItem orderItem = orderItemMapper.createRequestToEntity(orderItemCreateRequest);
        return orderItemMapper.toResponse(orderItemRepository.save(orderItem));

    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderUpdateRequest updateOrderRequest) {
        OrderItem newestOrderItem = findOrderItemByIdOrThrow(id);
        orderItemMapper.toUpdateRequest(newestOrderItem);

        OrderItem updateOrderItem = orderItemRepository.save(newestOrderItem);
        return orderItemMapper.toResponse(updateOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        OrderItem orderItem = findOrderItemByIdOrThrow(id);
        return orderItemMapper.toResponse(orderItem);
    }

    @Override
    public List<OrderItemResponse> getAllOrderItem() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toResponse)
                .toList();
    }

    private OrderItem findOrderItemByIdOrThrow(Long id){
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }
}

