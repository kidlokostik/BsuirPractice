package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(
        @NotNull(message = "Значение не может быть равно 0", groups = OnUpdate.class)
        Long id,
        @NotBlank(message = "Введите имя пользователя", groups = {OnUpdate.class, OnCreate.class})
        String name) {}
