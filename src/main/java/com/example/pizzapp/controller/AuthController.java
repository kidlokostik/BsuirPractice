package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.mapper.UserMapper;
import com.example.pizzapp.model.User;
import com.example.pizzapp.service.UserService;
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
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authentication(@RequestBody @Valid JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationService.authenticate(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserCreateRequest userCreateRequest){
        UserResponse userResponse = userService.createUser(userCreateRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam @Valid String refreshToken) {
        JwtResponse jwtResponse = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(jwtResponse);
    }
}