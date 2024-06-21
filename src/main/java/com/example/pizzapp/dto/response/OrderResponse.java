package com.example.pizzapp.dto.response;

import com.example.pizzapp.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        Long userId,
        String address,
        BigDecimal price,
        List<OrderItem> orderItems
){}
