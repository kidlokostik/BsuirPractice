package com.example.pizzapp.dto.response;

import com.example.pizzapp.models.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public record OrderResponse(
        Long id,
        Long userId,
        String address,
        BigDecimal price,
        ArrayList<OrderItem> orderItem
){}
