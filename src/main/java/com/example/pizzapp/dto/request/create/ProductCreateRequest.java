package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotBlank(message = "{field.required}")
        String name,
        @NotNull(message = "{field.null}")
        @Positive(message = "{field.positive}")
        BigDecimal price,
        @NotNull(message = "{field.null}")
        Long categoryId
){}

