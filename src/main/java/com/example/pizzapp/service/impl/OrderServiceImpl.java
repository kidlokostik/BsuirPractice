package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.OrderMapper;
import com.example.pizzapp.mapper.OrderItemMapper;
import com.example.pizzapp.model.Order;
import com.example.pizzapp.model.OrderItem;
import com.example.pizzapp.repository.OrderItemRepository;
import com.example.pizzapp.repository.OrderRepository;
import com.example.pizzapp.repository.ProductRepository;
import com.example.pizzapp.repository.UserRepository;
import com.example.pizzapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(OrderCreateRequest createOrderRequest) {
        Order order = orderMapper.createRequestToEntity(createOrderRequest);
        order.setUser(userRepository.findById(createOrderRequest.userId()).get());
        order.setPrice(new BigDecimal(0));
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order order = findOrderByIdOrThrow(id);
        orderMapper.updateOrderFromUpdateRequest(orderUpdateRequest, order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void addOrderItem(Long orderId, OrderItemCreateRequest orderItemCreateRequest) {
        Order order = findOrderByIdOrThrow(orderId);
        OrderItem orderItem = orderItemMapper.createRequestToEntity(orderItemCreateRequest);

        orderItem.setProduct(productRepository.findById(orderItemCreateRequest.productId()).get());
        orderItem.setOrder(orderRepository.findById(orderId).get());

        List<OrderItem> orderItemList = order.getOrderItems();
        orderItemList.add(orderItem);
        order.setOrderItems(orderItemList);

        Order savedOrder = orderRepository.save(order);
        updateOrderPrice(savedOrder);
        orderRepository.save(savedOrder);
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

    private void updateOrderPrice(Order order) {
        BigDecimal totalPrice = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setPrice(totalPrice);
    }
}
