package com.example.pizzapp.dto.response;
import com.example.pizzapp.model.Gender;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String phone,
        String name,
        String login,
        String email,
        Gender gender,
        LocalDate birthDate,
        String role
){}
