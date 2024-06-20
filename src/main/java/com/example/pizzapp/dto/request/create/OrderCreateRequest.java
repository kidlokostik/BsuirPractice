package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderCreateRequest(
        @NotBlank(message = "{field.required}")
        Long userId,
        @NotBlank(message = "{field.required}")
        String address,
        @NotNull(message = "{field.null}")
        @Positive(message = "{field.positive}")
        BigDecimal price
){}
