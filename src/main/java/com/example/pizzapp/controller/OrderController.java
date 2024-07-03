package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.OrderCreateRequest;
import com.example.pizzapp.dto.request.create.OrderItemCreateRequest;
import com.example.pizzapp.dto.request.update.OrderUpdateRequest;
import com.example.pizzapp.dto.response.OrderResponse;
import com.example.pizzapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("@E.canAccessUser(#orderCreateRequest.userId())")
    public OrderResponse createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        return orderService.createOrder(orderCreateRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public OrderResponse updateOrder(
            @PathVariable Long id,
            @RequestBody @Valid OrderUpdateRequest orderUpdateRequest
    ) {
        return orderService.updateOrder(id, orderUpdateRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public OrderResponse getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@E.canAccessOrder(#id)")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/{orderId}")
    @PreAuthorize("@E.canAccessOrder(#orderId)")
    public void addOrderItem(@PathVariable Long orderId,
                             @RequestBody OrderItemCreateRequest orderItemCreateRequest) {
        orderService.addOrderItem(orderId, orderItemCreateRequest);
    }

    @PostMapping("/{orderId}/items")
    @PreAuthorize("@E.canAccessOrder(#orderId)")
    public void addOrderItems(@PathVariable Long orderId,
                              @RequestBody List<OrderItemCreateRequest> orderItemCreateRequests) {
        orderService.addOrderItems(orderId, orderItemCreateRequests);
    }
}
