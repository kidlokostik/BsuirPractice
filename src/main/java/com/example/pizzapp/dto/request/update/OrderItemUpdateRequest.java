package com.example.pizzapp.dto.request.update;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record OrderItemUpdateRequest(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long productId,
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long orderId
){}
