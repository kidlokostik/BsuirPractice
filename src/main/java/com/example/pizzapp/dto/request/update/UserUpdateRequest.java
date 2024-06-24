package com.example.pizzapp.dto.request.update;

import jakarta.validation.constraints.*;

import java.util.Date;


public record UserUpdateRequest(
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
        String confirmPassword,

        @NotBlank(message = "{field.required}")
                String login,

        @NotBlank(message = "{field.required}")
        @Email(message = "{field.email}")
        String email,

        @NotBlank(message = "{field.required}")
        String gender,

        @NotNull(message = "{field.required}")
        @Past(message = "{field.past}")
        Date birthDate
        ){}
