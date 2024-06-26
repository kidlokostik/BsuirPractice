package com.example.pizzapp.controller;

import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login/email")
    public ResponseEntity<JwtResponse> loginByEmail(@RequestBody @Valid JwtRequest loginRequest) {
        JwtResponse jwtResponse = authenticationService.loginByEmail(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/login/name")
    public ResponseEntity<JwtResponse> loginByName(@RequestBody @Valid JwtRequest loginRequest) {
        JwtResponse jwtResponse = authenticationService.loginByName(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam @Valid String refreshToken) {
        JwtResponse jwtResponse = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(jwtResponse);
    }
}