package com.example.pizzapp.dto.request.create;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record OrderItemCreateRequest(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotNull(message = "{field.null}", groups = {OnUpdate.class, OnCreate.class})
        Long productId,
        @NotNull(message = "{field.null}", groups = {OnUpdate.class, OnCreate.class})
        Long orderId
){}
