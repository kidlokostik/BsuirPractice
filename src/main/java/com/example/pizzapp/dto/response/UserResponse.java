package com.example.pizzapp.dto.response;

import com.example.pizzapp.dto.Role;

public record UserResponse(
        Long id,
        String phone,
        String name,
        Role role
){}
