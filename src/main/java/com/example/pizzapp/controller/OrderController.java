package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/new")
    @PreAuthorize("@E.canAccessUser(#req.userId())")
    public OrderResponse createOrder(@RequestBody @Valid @P("req") OrderCreateRequest orderCreateRequest) {
        return orderService.createOrder(orderCreateRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public OrderResponse updateOrder(
            @PathVariable @P("id") Long id,
            @RequestBody @Valid OrderUpdateRequest orderUpdateRequest
    ) {
        return orderService.updateOrder(id, orderUpdateRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public OrderResponse getById(@PathVariable @P("id") Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public void deleteOrder(@PathVariable @P("id") Long id) {
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
    public void addOrderItems(@PathVariable Long orderId, List<OrderItemCreateRequest> orderItemCreateRequests) {
        orderService.addOrderItems(orderId, orderItemCreateRequests);
    }
}
