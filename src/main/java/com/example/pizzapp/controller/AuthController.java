package com.example.pizzapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pizzapp.security.dto.*;
import com.example.pizzapp.service.AuthenticationService;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtResponse> authentication(@RequestBody @Valid JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationService.authenticate(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam @Valid String refreshToken) {
        JwtResponse jwtResponse = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(jwtResponse);
    }
}