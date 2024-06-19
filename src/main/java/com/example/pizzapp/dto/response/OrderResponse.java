package com.example.pizzapp.dto.response;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        Long userId,
        String address,
        BigDecimal price
){}
