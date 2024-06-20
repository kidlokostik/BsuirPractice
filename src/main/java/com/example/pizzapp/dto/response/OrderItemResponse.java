package com.example.pizzapp.dto.response;

public record OrderItemResponse(
        Long id,
        ProductResponse product,
        Long quantity
){}
