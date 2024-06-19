package com.example.pizzapp.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotBlank(message = "{field.required}")
        String name
){}
