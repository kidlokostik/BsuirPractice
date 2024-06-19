package com.example.pizzapp.dto.response;

public record UserResponse(
        Long id,
        String phone,
        String name,
        String password,
        String confirmPassword
){}
