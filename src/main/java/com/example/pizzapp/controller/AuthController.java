package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public JwtResponse registration(@RequestBody UserCreateRequest userCreateRequest) {
        return authenticationService.registration(userCreateRequest);
    }

    @PostMapping("/authentication")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) {
        return authenticationService.authentication(jwtRequest);
    }

    @PostMapping("/refresh")
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        authenticationService.refreshToken(httpServletRequest, httpServletResponse);
    }
}