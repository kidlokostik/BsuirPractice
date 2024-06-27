package com.example.pizzapp.service;

import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;

public interface AuthenticationService {

    JwtResponse authenticate(JwtRequest jwtRequest);

    JwtResponse refreshToken(String refreshToken);
}
