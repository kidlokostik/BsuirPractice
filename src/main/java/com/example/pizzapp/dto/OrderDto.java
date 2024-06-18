package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderDto(@NotNull(message = "Значение не может быть равно null", groups = OnUpdate.class)
                       Long id,
                       @NotBlank(message = "Имя пользователя не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
                       Long user,
                       @NotBlank(message = "Адресс пользователя не должен быть пустым", groups = {OnUpdate.class, OnCreate.class})
                       String address,
                       @NotNull(message = "Цена не должна быть null", groups = {OnUpdate.class, OnCreate.class})
                       @Positive(message = "Цена должна быть положительной", groups = {OnUpdate.class, OnCreate.class})
                       BigDecimal price) { }
