package com.example.pizzapp.security.dto;

public record JwtResponse (
        Long id,
        String login,
        String accessToken,
        String refreshToken
) {}
