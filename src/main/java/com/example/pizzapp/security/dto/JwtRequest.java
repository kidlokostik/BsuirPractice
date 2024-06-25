package com.example.pizzapp.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotNull(message = "{field.required}")
    String email;

    @NotNull(message = "{field.required}")
    String password;
}
