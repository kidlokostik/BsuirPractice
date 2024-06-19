package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotBlank(message = "{field.required}")
        String name
){}
