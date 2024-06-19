package com.example.pizzapp.dto.response;

public record OrderItemResponse(
        Long id,
        Long productId,
        Long orderId
){}
