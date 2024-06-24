package com.example.pizzapp.dto.response;

public record UserResponse(
        Long id,
        String phone,
        String name,
        String login,
        String email,
        String gender,
        java.util.Date birthDate,
        String role
){}
