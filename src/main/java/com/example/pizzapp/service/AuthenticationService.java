package com.example.pizzapp.service;

import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;

public interface AuthenticationService {

    JwtResponse loginByEmail(JwtRequest loginRequest);

    JwtResponse loginByName(JwtRequest loginRequest);

    JwtResponse refreshToken(String refreshToken);
}
