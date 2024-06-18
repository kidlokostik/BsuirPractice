package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(@NotNull(message = "Значение не может быть равно null", groups = OnUpdate.class)
                         Long id,
                         @NotBlank(message = "Название товара не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
                         String name,
                         @NotNull(message = "Значение не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
                         Long categoryId) {}
