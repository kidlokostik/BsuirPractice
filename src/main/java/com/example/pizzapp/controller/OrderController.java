package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        return orderService.createOrder(orderCreateRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping
    public void addOrderItem(Long orderId, OrderItemCreateRequest orderItemCreateRequest) {
        orderService.addOrderItem(orderId, orderItemCreateRequest);
    }

    @PostMapping("/{orderId}")
    public void addOrderItems(Long orderId, List<OrderItemCreateRequest> orderItemCreateRequests) {
        orderService.addOrderItems(orderId, orderItemCreateRequests);
    }
}
