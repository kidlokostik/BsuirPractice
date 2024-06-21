package com.example.pizzapp.dto.response;

import com.example.pizzapp.models.Order;

import java.util.List;

public record UserResponse(
        Long id,
        String phone,
        String name
){}
