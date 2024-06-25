package com.example.pizzapp.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotBlank(message = "{field.required}")
    String email;

    @NotBlank(message = "{field.required}")
    String password;
}
