package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotNull;

public record OrderItemCreateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotNull(message = "{field.null}")
        Long productId,
        @NotNull(message = "{field.null}")
        Long orderId
){}
