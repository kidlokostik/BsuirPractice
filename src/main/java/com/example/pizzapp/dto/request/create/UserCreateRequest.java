package com.example.pizzapp.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record UserCreateRequest(
        @NotNull(message = "{field.null}")
        Long id,
        @NotBlank(message = "{field.required}")
        @Pattern(regexp = "\\+?[0-9]{7,15}", message = "{field.phone}")
        String phone,
        @NotBlank(message = "{field.required}")
        String name,
        @NotBlank(message = "{field.required}")
        @Size(min = 6, message = "{field.size}")
        String password,
        @NotBlank(message = "{field.required}")
        @Size(min = 6, message = "{field.size}")
        String confirmPassword
){}
