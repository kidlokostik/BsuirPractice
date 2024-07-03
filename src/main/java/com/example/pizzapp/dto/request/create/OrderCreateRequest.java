package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderCreateRequest(
        @NotNull(message = "{field.required}")
        Long userId,
        @NotBlank(message = "{field.required}")
        String address
){}
