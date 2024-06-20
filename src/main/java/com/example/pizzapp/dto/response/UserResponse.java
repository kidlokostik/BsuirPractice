package com.example.pizzapp.dto.response;

import com.example.pizzapp.models.Order;

import java.util.ArrayList;

public record UserResponse(
        Long id,
        String phone,
        String name,
        String password,
        String confirmPassword,
        ArrayList<Order> orders
){}
