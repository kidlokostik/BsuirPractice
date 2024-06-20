package com.example.pizzapp.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemUpdateRequest(
        @NotNull(message = "{field.null}")
        Long productId,
        @NotNull(message = "{field.null}")
        @Positive(message = "{field.positive}")
        Long quantity
){}
