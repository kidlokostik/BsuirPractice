package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.*;


public record UserDto(
        @NotNull(message = "{field.null}", groups = OnUpdate.class)
        Long id,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        @Pattern(regexp = "\\+?[0-9]{7,15}", message = "{field.phone}", groups = {OnUpdate.class, OnCreate.class})
        String phone,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        String name,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        @Size(min = 6, message = "{field.size}", groups = {OnUpdate.class, OnCreate.class})
        String password,
        @NotBlank(message = "{field.required}", groups = {OnUpdate.class, OnCreate.class})
        @Size(min = 6, message = "{field.size}", groups = {OnUpdate.class, OnCreate.class})
        String confirmPassword){}
