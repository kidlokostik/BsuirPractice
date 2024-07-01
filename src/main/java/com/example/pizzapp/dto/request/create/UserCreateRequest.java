package com.example.pizzapp.dto.request.create;

import com.example.pizzapp.model.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record UserCreateRequest(
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

        @NotNull(message = "{field.required}")
        Gender gender,

        @NotNull(message = "{field.required}")
        @Past(message = "{field.past}")
        LocalDate birthDate
){}
