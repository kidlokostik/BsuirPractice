package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDto(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        String name,
        @NotNull(message = "{field.null}", groups = {OnUpdate.class, OnCreate.class})
        @Positive(message = "{field.positive}", groups = {OnUpdate.class, OnCreate.class})
        BigDecimal price,
        @NotNull(message = "{field.null}", groups = {OnUpdate.class, OnCreate.class})
        Long categoryId
){}

