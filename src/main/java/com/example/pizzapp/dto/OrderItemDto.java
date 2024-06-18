package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record OrderItemDto(@NotNull(message = "Значение id не может быть равно null", groups = OnUpdate.class)
                           Long id,
                           @NotNull(message = "Значение product не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
                           Long productId,
                           @NotNull(message = "Значение order не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
                           Long orderId) {}
