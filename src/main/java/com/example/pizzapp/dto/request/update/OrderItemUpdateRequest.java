package com.example.pizzapp.dto.request.update;

import jakarta.validation.constraints.NotNull;

public record OrderItemUpdateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotNull(message = "{field.null}")
        Long productId,
        @NotNull(message = "{field.null}")
        Long orderId
){}
