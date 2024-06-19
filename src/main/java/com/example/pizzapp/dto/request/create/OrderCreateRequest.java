package com.example.pizzapp.dto.request.create;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderCreateRequest(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        Long userId,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        String address,
        @NotNull(message = "{field.null}", groups = {OnUpdate.class, OnCreate.class})
        @Positive(message = "{field.positive}", groups = {OnUpdate.class, OnCreate.class})
        BigDecimal price
){}
