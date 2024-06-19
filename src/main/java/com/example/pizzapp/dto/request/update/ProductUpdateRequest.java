package com.example.pizzapp.dto.request.update;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotBlank(message = "{field.required}", groups = OnUpdate.class)
        String name,
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        @Positive(message = "{field.positive}", groups = OnUpdate.class)
        BigDecimal price,
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long categoryId
){}

