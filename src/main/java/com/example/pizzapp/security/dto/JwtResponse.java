package com.example.pizzapp.security.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;
    private String email;
    private String login;
    private String accessToken;
    private String refreshToken;
}
