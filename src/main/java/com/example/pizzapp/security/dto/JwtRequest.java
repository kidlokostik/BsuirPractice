package com.example.pizzapp.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record JwtRequest(
        @NotBlank(message = "{field.required}")
        String login,

        @NotBlank(message = "{field.required}")
        String password
) {}
