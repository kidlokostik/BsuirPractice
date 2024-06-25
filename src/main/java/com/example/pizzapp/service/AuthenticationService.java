package com.example.pizzapp.service;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    JwtResponse registration(UserCreateRequest userCreateRequest);

    JwtResponse authentication(JwtRequest jwtRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response);
}
